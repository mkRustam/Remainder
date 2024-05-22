package com.projects.data.modules.base.strategy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status

abstract class LoadDataStrategy<Db, Domain, Api, Request: LoadRequest>: IDataStrategy<Domain, Request> {

    @WorkerThread
    abstract suspend fun fetchFromLocal(request: Request): Flow<Db?>

    @WorkerThread
    abstract suspend fun mapDbData(request: Request, data: Db?): Domain?

    @WorkerThread
    abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    abstract suspend fun mapApiData(request: Request, data: Api?): Db?

    @WorkerThread
    abstract suspend fun saveData(request: Request, data: Db?)

    @WorkerThread
    abstract suspend fun isActualData(data: Db): Boolean

    override fun execute(request: Request): Flow<Resource<Domain>> = flow {
        if(!request.refresh) {
            return@flow fetchFromLocal(request).collect { dbResult ->
                if(dbResult != null && isActualData(dbResult)) emit(Resource.success(mapDbData(request, dbResult)))
                else executeApiRequest(this, request)
            }
        } else {
            executeApiRequest(this, request)
        }
    }

    private suspend fun executeApiRequest(collector: FlowCollector<Resource<Domain>>, request: Request) {
        collector.emit(Resource.loading(null))
        val apiResult = fetchFromRemote(request)
        if(apiResult.status == Status.SUCCESS) {
            val data = mapApiData(request, apiResult.data)
            saveData(request, data)
            fetchFromLocal(request).collect { collector.emit(Resource.success(mapDbData(request, it))) }
        }
        else
            collector.emit(Resource.error(apiResult.message, null))
    }
}