package com.projects.data.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.projects.data.network.api.ApiRequests
import com.projects.data.network.deserializer.DateDeserializer
import com.projects.data.Constants
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleNetwork {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://crudcrud.com/api/3eb81bb722704239859680a0c2a48320/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideRequests(retrofit: Retrofit) : ApiRequests {
        return retrofit.create(ApiRequests::class.java)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .setDateFormat(Constants.Formats.DATE_NETWORK)
        .create()
}