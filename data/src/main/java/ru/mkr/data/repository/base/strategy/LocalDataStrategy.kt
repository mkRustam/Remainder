package ru.mkr.data.repository.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import ru.mkr.data.repository.base.RepositoryBase
import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource
import java.lang.Exception

abstract class LocalDataStrategy<Cache, Domain, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    public abstract suspend fun fetchFromLocal(request: Request): Flow<Cache?>

    @WorkerThread
    public abstract suspend fun mapDbData(request: Request, data: Cache?): Domain?

    @WorkerThread
    public abstract suspend fun isActualData(data: Cache): Boolean

    override fun execute(request: Request): Flow<Resource<Domain>> = flow {
        try {
            val data = fetchFromLocal(request).first()
            if(data != null && isActualData(data))
                emit(Resource.success(mapDbData(request, data)))
            else
                emit(Resource.error("Invalid cache", null)) // TODO: Extract text
        }
        catch(ex: Exception) {
            // Ошибка в сценарии
            emit(Resource.error(ex.localizedMessage ?: RepositoryBase.ERROR_UNKNOWN, null))
        }
    }
}