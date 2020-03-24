package com.example.covidmapper

import retrofit2.Call
import retrofit2.http.GET

internal interface CoronaService {
    @GET("coronavirus")
    fun getData(): Call<String>
}