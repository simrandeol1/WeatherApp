package com.example.weatherapp.network

import com.example.weatherapp.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("/data/2.5/forecast")
    fun getWeatherByCity(@Query("q") city:String, @Query("appid") appid:String = "16a9b95068c64646b851651f357106ac") : Call<WeatherData>
}