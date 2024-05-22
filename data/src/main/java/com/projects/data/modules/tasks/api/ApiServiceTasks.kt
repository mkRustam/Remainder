package com.projects.data.modules.tasks.api

import retrofit2.Response
import retrofit2.http.*
import com.projects.data.modules.tasks.api.entity.EntityApiTask

interface ApiServiceTasks {
    
    @GET("tasks")
    suspend fun getAll(): Response<List<EntityApiTask>>

    @GET("tasks/{id}")
    suspend fun get(@Path("id") id: String): Response<EntityApiTask>

    @POST("tasks")
    suspend fun add(@Body task: EntityApiTask): Response<EntityApiTask>

    @DELETE("tasks/{id}")
    suspend fun delete(@Path("id") id: String): Response<Unit>

    @PUT("tasks/{id}")
    suspend fun update(@Path("id") id: String, @Body task: EntityApiTask): Response<Unit>
}