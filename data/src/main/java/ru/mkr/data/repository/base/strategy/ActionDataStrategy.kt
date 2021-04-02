package ru.mkr.data.repository.base.strategy

import androidx.annotation.WorkerThread
import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.entity.Status

abstract class ActionDataStrategy<Domain, Api, Request: LoadRequest>: IRequestDataStrategy<Domain, Request> {

    @WorkerThread
    public abstract suspend fun fetchFromRemote(request: Request): Resource<Api?>

    @WorkerThread
    public abstract suspend fun handleResult(request: Request, data: Api?): Domain?

    override suspend fun execute(request: Request): Resource<Domain> {
        val apiResult = fetchFromRemote(request)
        if(apiResult.status == Status.SUCCESS) {
            return Resource.success(handleResult(request, apiResult.data))
        }
        else
            return Resource.error(apiResult.message, null)
    }
}