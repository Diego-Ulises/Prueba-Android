package com.example.puebaandroid.api

import retrofit2.Call
import com.example.puebaandroid.models.WeatherResponse
import retrofit2.http.*

interface ApiOpenWeather {

    @Headers("x-api-key: 0d2595d04fb2f1281f818a7ce08eb83c")
    @POST("data/2.5/weather?q=morelia,mx")
    fun getDataWeather(

    ):Call<WeatherResponse>
}