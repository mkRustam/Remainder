package ru.mkr.data.network

import retrofit2.Response
import ru.mkr.domain.entity.Resource

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