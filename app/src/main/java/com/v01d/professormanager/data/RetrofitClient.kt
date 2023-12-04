package com.v01d.professormanager.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var instance: RetrofitClient? = null
    lateinit var springAPI: SpringAPI

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(SpringAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        springAPI = retrofit.create(SpringAPI::class.java)
    }

    @Synchronized
    fun getInstance(): RetrofitClient {
        if (instance == null) {
            instance = RetrofitClient
            initRetrofit()
        }
        return instance!!
    }
}
