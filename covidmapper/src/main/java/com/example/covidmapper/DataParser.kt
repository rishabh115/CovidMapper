package com.example.covidmapper

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class DataParser(private val listener: DataListener) {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://www.worldometers.info/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    fun getCountriesData() {
        val  service = retrofit.create(CoronaService::class.java)
        service.getData().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    val list = Utils.parseHtmlData(response.body())
                    listener.onDataReceived(Success(list))
                } else {
                    listener.onDataReceived(Failure())
                }
            }

            override fun onFailure(p0: Call<String>, p1: Throwable) {
                listener.onDataReceived(Failure())
            }
        })
    }
}


