package ru.mkr.data.repository.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import java.lang.Exception

abstract class NetworkBoundRepository<Cache, Api, Domain> {

    /**
     * Parsing Cache->Domain for loading and success events
     * */
    @WorkerThread
    protected abstract suspend fun parseData(data: Cache?): Domain?

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<DbData<Cache?>>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Resource<Api?>

    /**
     * Saves retrieved from remote into the persistence storage.
     * @return Data stored in cache or not. If false see [noStore]
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(data: Api?): Boolean

    /**
     * Converting Api->Cache for Cache->Domain to avoid storing in Cache.
     * If [saveRemoteData] is true - ignoring this method
     * */
    @WorkerThread
    protected fun noStore(data: Api?): Cache? {
        return null
    }


    fun asFlow(dataResource: DataResource) = flow<Resource<Domain>> {
        try {
            var apiResponse: Resource<Api?>? = null
            val resultFromCache: DbData<Cache?>
            var cachedData: Cache? = null
            when {
                DataResource.REFRESH == dataResource -> {
                    emit(Resource.loading(null))
                    // Шаг 1. Загружаем данные из Api
                    apiResponse = fetchFromRemote()
                }
                DataResource.CACHED == dataResource -> {
                    // Шаг 1. Отдаем данные из локального хранилища
                    resultFromCache = fetchFromLocal().first()
                    emit(Resource.success(if(resultFromCache.isExpired) parseData(resultFromCache.data) else null))
                    return@flow
                }
                DataResource.COMMON == dataResource -> {
                    // Шаг 1. Пытаемся отдать предварительные данные из локального хранилища
                    resultFromCache = fetchFromLocal().first()
                    cachedData = if(!resultFromCache.isExpired) resultFromCache.data else null
                    emit(Resource.loading(parseData(cachedData)))
                    // Шаг 2. Загружаем данные из Api
                    apiResponse = fetchFromRemote()
                }
            }

            // Шаг 3. Обрабатываем полученные данные
            if(apiResponse?.status == Status.SUCCESS) {
                // Если данные кэшируемые, то сохраняем их в хранилище и повторно обращаемся к нему
                if(saveRemoteData(apiResponse.data))
                    fetchFromLocal().collect { emit(Resource.success(parseData(it.data))) }
                else {
                    // Если данные нельзя кэшировать, то используем промежуточную конвертацию Api->Cache для Cache->Domain
                    val preparedCache = noStore(apiResponse.data)
                    if(preparedCache != null) emit(Resource.success(parseData(preparedCache)))
                    else emit(Resource.success(null))
                }
            }
            else if(apiResponse?.status == Status.ERROR) emit(Resource.error(apiResponse.message!!, parseData(cachedData)))
            else emit(Resource.error(null, parseData(cachedData)))
        }
        catch(ex: Exception) {
            // Ошибка в сценарии
            emit(Resource.error(ex.localizedMessage ?: RepositoryBase.ERROR_UNKNOWN, null))
        }
    }



}