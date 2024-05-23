package com.projects.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryUser
import com.projects.domain.usecase.UseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseUserRegister @Inject constructor(
    private val repositoryUser: IRepositoryUser,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<UseCaseUserRegister.Params, EntityUser>(dispatcher) {

    override suspend fun execute(parameters: Params): Resource<EntityUser> {
        return repositoryUser.register(parameters.login, parameters.password)
    }

    data class Params(
        var login: String,
        var password: String
    )
}