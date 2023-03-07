package com.example.weatherapp.repository

import com.example.weatherapp.WeatherData
import com.example.weatherapp.dao.WeatherDao
import com.example.weatherapp.entity.CityData
import com.example.weatherapp.network.APIInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiInterface: APIInterface, val weatherDao: WeatherDao) {

    private fun refreshWeather(city: String){
        val weatherData = apiInterface.getWeatherByCity(city)
        weatherData.enqueue(object : Callback<WeatherData> {
            //throw an exception if something bad happens
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                //display the error message

            }

            //show the result of the query based on the returned response parameter
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherResult: WeatherData? = response.body()
                if (weatherResult != null) {
                    GlobalScope.launch(Dispatchers.IO) {
                        weatherDao.insert(CityData(weatherResult.city?.id?.toLong(), weatherResult.city?.name, weatherResult.list?.get(0)?.main?.temp.toString(), weatherResult.list?.get(0)?.main?.humidity.toString()))
                    }
                }
            }
        })
    }

    fun getWeather(city: String, mutableCityData: MutableStateFlow<CityData?>) {
        GlobalScope.launch(Dispatchers.IO) {
            refreshWeather(city)
            setWeather(weatherDao.findByCity(city), mutableCityData)
        }
    }

    private suspend fun setWeather(cityData: CityData, mutableCityData: MutableStateFlow<CityData?>){
        withContext(Dispatchers.Main) {
            mutableCityData.value = cityData
        }
    }
}
