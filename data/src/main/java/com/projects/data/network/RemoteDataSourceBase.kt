package com.projects.data.network

import retrofit2.Response
import com.projects.domain.entity.Resource

abstract class RemoteDataSourceBase {

    suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                return Resource.success(response.body())
            }
            return Resource.error(" ${response.code()} ${response.message()}", null)
        } catch (e: Exception) {
            return Resource.error(e.message ?: e.toString(), null)
        }
    }
}