package ru.mkr.data.repository.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status

abstract class LoadDataStrategy<Cache, Domain, Api, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    public abstract suspend fun fetchFromLocal(request: Request): Flow<Cache?>

    @WorkerThread
    public abstract suspend fun mapDbData(request: Request, data: Cache?): Domain?

    @WorkerThread
    public abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    public abstract suspend fun mapApiData(request: Request, data: Api?): Cache?

    @WorkerThread
    public abstract suspend fun saveData(request: Request, data: Cache?)

    @WorkerThread
    public abstract suspend fun isActualData(data: Cache): Boolean

    override fun execute(request: Request): Flow<Resource<Domain>> = flow {
        if(!request.refresh) {
            val cacheResult = fetchFromLocal(request).first()
            if(cacheResult != null && isActualData(cacheResult)) emit(Resource.loading(mapDbData(request, cacheResult)))
        }

        val apiResult = fetchFromRemote(request)
        if(apiResult.status == Status.SUCCESS) {
            val data = mapApiData(request, apiResult.data)
            saveData(request, data)
            fetchFromLocal(request).collect { emit(Resource.success(mapDbData(request, it))) }
        }
        else
            emit(Resource.error(apiResult.message, null))
    }
}