package ru.mkr.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryUser
import ru.mkr.domain.usecase.UseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
import javax.inject.Inject

class UseCaseUserLogout @Inject constructor(
    private val repositoryUser: IRepositoryUser,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(parameters: Unit): Resource<Boolean> {
        return repositoryUser.logout()
    }
}