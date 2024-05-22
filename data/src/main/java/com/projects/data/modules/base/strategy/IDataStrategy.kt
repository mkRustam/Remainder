package com.projects.data.modules.base.strategy

import kotlinx.coroutines.flow.Flow
import com.projects.data.modules.base.LoadRequest
import com.projects.domain.entity.Resource

interface IDataStrategy<Domain, Request: LoadRequest> {
    fun execute(request: Request): Flow<Resource<Domain>>
}