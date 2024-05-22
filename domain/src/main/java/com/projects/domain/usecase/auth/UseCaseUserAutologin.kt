package com.projects.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryUser
import com.projects.domain.usecase.UseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseUserAutologin @Inject constructor(
    private val repositoryUser: IRepositoryUser,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, EntityUser>(dispatcher) {

    override suspend fun execute(parameters: Unit): Resource<EntityUser> {
        return repositoryUser.autologin()
    }
}