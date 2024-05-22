package com.projects.data.modules.base.strategy

import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource

interface IRequestDataStrategy<Domain, Request: LoadRequest> {
    suspend fun execute(request: Request): Resource<Domain>
}