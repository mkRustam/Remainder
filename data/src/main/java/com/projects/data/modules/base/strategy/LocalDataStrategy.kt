package com.projects.data.modules.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import com.projects.data.modules.base.RepositoryBase
import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource
import java.lang.Exception

abstract class LocalDataStrategy<Db, Domain, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    protected abstract suspend fun fetchFromLocal(request: Request): Flow<Db?>

    @WorkerThread
    protected abstract suspend fun mapDbData(request: Request, data: Db?): Domain?

    @WorkerThread
    protected abstract suspend fun isActualData(data: Db): Boolean

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