package ru.mvrlrd.pokeapi.data.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

internal const val BASE_URL = "https://pokeapi.co/api/v2/"

@Singleton
class RetrofitClient @Inject constructor() {
    private val retrofit: Retrofit
    init {
        retrofit = getClient()
    }

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}