package com.projects.data.modules.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import com.projects.data.modules.auth.mapper.MapperFirebaseUser
import com.projects.data.modules.base.RepositoryBase
import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource
import com.projects.domain.entity.Status
import com.projects.domain.repository.IRepositoryUser
import javax.inject.Inject

class RepositoryUser  @Inject constructor(
    private var auth: FirebaseAuth,
    private var mapperFirebaseUser: MapperFirebaseUser
) : RepositoryBase(), IRepositoryUser {

    override suspend fun isAuthenticated() : Resource<Boolean> {
        val currentUser = getCurrentUser()
        return Resource.success(currentUser.data != null)
    }

    override suspend fun login(email: String, pass: String) = handleAuthResult(auth.signInWithEmailAndPassword(email, pass))

    override suspend fun loginAnonymously(): Resource<EntityUser> {
        auth.signInAnonymously()
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Resource<Boolean> {
        auth.signOut()
        return Resource.success(true)
    }

    override suspend fun autologin(): Resource<EntityUser> {
        return getCurrentUser()
    }

    override suspend fun register(email: String, pass: String) = handleAuthResult(auth.createUserWithEmailAndPassword(email, pass))

    private suspend fun handleAuthResult(task: Task<AuthResult>): Resource<EntityUser> {
        try {
            task.await()
            if(task.isSuccessful) {
                val currentUser = getCurrentUser()
                if(currentUser.status == Status.SUCCESS && currentUser.data != null) return currentUser
                else return Resource.error(null, null)
            }
            else return Resource.error(task.exception?.localizedMessage, null)
        }
        catch(ex: FirebaseAuthException) {
            return Resource.error(ex.localizedMessage ?: null, null)
        }
    }

    private fun getCurrentUser(): Resource<EntityUser> {
        val firebaseUser = auth.currentUser
        return if(firebaseUser != null) Resource.success(mapperFirebaseUser.fromDbToDomain(firebaseUser))
        else Resource.error(null, null)
    }
}