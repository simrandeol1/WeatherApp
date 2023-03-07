package com.example.weatherapp

data class WeatherData(val list: List<Weather>?, val city: City?)

data class Main(val temp: Number?, val pressure: Number?, val humidity: Number?, val temp_min: Number?, val temp_max: Number?)

data class Weather(val id: Number?, val main: Main?, val description: String?, val icon: String?)

data class City(val id: Number?, val name: String?)
