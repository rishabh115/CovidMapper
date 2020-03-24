package com.example.covidmapper

sealed class Response
class Success(val data: List<CountryData>):Response()
class Failure: Response()