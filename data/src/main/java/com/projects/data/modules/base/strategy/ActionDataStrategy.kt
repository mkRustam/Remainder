package com.projects.data.modules.base.strategy

import androidx.annotation.WorkerThread
import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status

abstract class ActionDataStrategy<Domain, Api, Request: LoadRequest>: IRequestDataStrategy<Domain, Request> {

    @WorkerThread
    protected abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    protected abstract suspend fun handleResult(request: Request, data: Api?): Domain?

    override suspend fun execute(request: Request): Resource<Domain> {
        val apiResult = fetchFromRemote(request)
        if(apiResult.status == Status.SUCCESS) {
            return Resource.success(handleResult(request, apiResult.data))
        }
        else
            return Resource.error(apiResult.message, null)
    }
}