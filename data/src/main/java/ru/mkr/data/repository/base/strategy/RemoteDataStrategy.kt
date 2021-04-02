package ru.mkr.data.repository.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mkr.data.repository.base.RepositoryBase
import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status
import java.lang.Exception

abstract class RemoteDataStrategy<Cache, Domain, Api, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    public abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    public abstract suspend fun mapApiData(request: Request, data: Api?): Cache?

    @WorkerThread
    public abstract suspend fun mapDbData(request: Request, data: Cache?): Domain?

    override fun execute(request: Request): Flow<Resource<Domain>> = flow {
        try {
            val result = fetchFromRemote(request)
            if(result.status == Status.SUCCESS) {
                val data = mapDbData(request, mapApiData(request, result.data))
                emit(Resource.success(data))
            }
            else
                emit(Resource.error(result.message, null))
        }
        catch(ex: Exception) {
            // Ошибка в сценарии
            emit(Resource.error(ex.localizedMessage ?: RepositoryBase.ERROR_UNKNOWN, null))
        }
    }
}