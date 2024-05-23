package com.projects.data.modules.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.projects.data.modules.base.RepositoryBase
import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import java.lang.Exception

abstract class RemoteDataStrategy<Db, Domain, Api, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    abstract suspend fun mapApiData(request: Request, data: Api?): Db?

    @WorkerThread
    abstract suspend fun mapDbData(request: Request, data: Db?): Domain?

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