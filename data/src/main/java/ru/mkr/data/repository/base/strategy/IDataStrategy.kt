package ru.mkr.data.repository.base.strategy

import kotlinx.coroutines.flow.Flow
import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource

interface IDataStrategy<Domain, Request: LoadRequest> {
    fun execute(request: Request): Flow<Resource<Domain>>
}