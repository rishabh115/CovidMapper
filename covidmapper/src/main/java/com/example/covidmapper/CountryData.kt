package com.example.covidmapper

data class CountryData (val country: String, val totalCases: Int, val newCases: Int,
                        val totalDeaths: Int, val newDeaths: Int, val totalRecovered: Int,
                        val activeCases:Int, val criticalCases:Int)