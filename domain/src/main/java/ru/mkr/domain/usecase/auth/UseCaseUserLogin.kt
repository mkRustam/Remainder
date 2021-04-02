package ru.mkr.domain.usecase.auth

import kotlinx.coroutines.CoroutineDispatcher
import ru.mkr.domain.entity.EntityUser
import ru.mkr.domain.entity.Resource
import ru.mkr.domain.repository.IRepositoryUser
import ru.mkr.domain.usecase.UseCase
import ru.mkr.domain.utils.annotations.IoDispatcher
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