package com.projects.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import com.projects.domain.entity.Resource
import com.projects.domain.repository.IRepositoryUser
import com.projects.domain.usecase.UseCase
import com.projects.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseUserLogout @Inject constructor(
    private val repositoryUser: IRepositoryUser,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(parameters: Unit): Resource<Boolean> {
        return repositoryUser.logout()
    }
}