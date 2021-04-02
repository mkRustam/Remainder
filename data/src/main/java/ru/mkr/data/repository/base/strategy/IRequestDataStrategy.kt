package ru.mkr.data.repository.base.strategy

import ru.mkr.data.repository.base.LoadRequest
import ru.mkr.domain.entity.Resource

interface IRequestDataStrategy<Domain, Request: LoadRequest> {
    suspend fun execute(request: Request): Resource<Domain>
}