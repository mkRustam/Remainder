package ru.mkr.data.repository.base

import kotlinx.coroutines.flow.*
import ru.mkr.data.repository.base.cache.CacheController
import ru.mkr.domain.entity.Resource
import java.util.*

/**
 * @param mapDb: Only for cached data
 * @param mapNet: If Api request returns something to parse
 * */
abstract class RepositoryBase {

    companion object {
        const val ERROR_UNKNOWN = "Unknown error"
    }

    /**
     * @param mapCache Маппер для cache-сущностей
     * @param fromCache Нужны данные только из кэша
     * */

    fun <Cache, Api, Domain> loadData(
        databaseQuery: () -> Flow<Cache?>,
        networkHandler: INetworkRequestHandler<Api>,
        cacheController: CacheController? = null,
        mapCache: ((Cache) -> Domain),
        dataResource: DataResource = DataResource.COMMON
    ): Flow<Resource<Domain>> = object: NetworkBoundRepository<Cache, Api, Domain>() {
        override suspend fun parseData(data: Cache?): Domain? {
            return if(data != null) mapCache.invoke(data)
            else null
        }

        override fun fetchFromLocal(): Flow<DbData<Cache?>> = flow {
            databaseQuery.invoke().collect { dataDb ->
                // Проверка данных на актуальность
                val isExpired = if(dataDb != null) isCacheActual(cacheController, dataDb) else false
                emit(DbData(dataDb, isExpired))
            }
        }

        override suspend fun fetchFromRemote(): Resource<Api> {
            return networkHandler.makeRequest()
        }

        override suspend fun saveRemoteData(data: Api?): Boolean {
            return networkHandler.onResponse(data)
        }
    }.asFlow(dataResource)

    private fun isCacheActual(cacheController: CacheController?, data: Any): Boolean {
        return cacheController?.isRelevant(data) ?: return true
    }

    interface INetworkRequestHandler<R> {
        suspend fun makeRequest(): Resource<R>
        /**
         * @return Результат сохранен в локальном хранилище
         * */
        suspend fun onResponse(data: R?): Boolean { return false }
    }
}