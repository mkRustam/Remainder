package com.projects.domain.repository

import com.projects.domain.entity.EntityUser
import com.projects.domain.entity.Resource

interface IRepositoryUser {
    suspend fun isAuthenticated(): Resource<Boolean>

    suspend fun login(email: String, pass: String): Resource<EntityUser>
    suspend fun loginAnonymously(): Resource<EntityUser>
    suspend fun logout(): Resource<Boolean>
    suspend fun autologin(): Resource<EntityUser>

    suspend fun register(email: String, pass: String): Resource<EntityUser>
}