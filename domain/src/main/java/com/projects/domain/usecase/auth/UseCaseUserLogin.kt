package com.projects.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryUser
import com.projects.domain.usecase.UseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseUserLogin @Inject constructor(
    private val repositoryUser: IRepositoryUser,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<UseCaseUserLogin.Params, EntityUser>(dispatcher) {

    override suspend fun execute(parameters: Params): Resource<EntityUser> {
        return repositoryUser.login(parameters.login, parameters.password)
    }

    public data class Params(
        var login: String,
        var password: String
    )
}