package com.example.covidmapper

interface DataListener{
    fun onDataReceived(response: Response)
}