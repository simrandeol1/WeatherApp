package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.MyApplication
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.entity.CityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel: ViewModel() {

    @Inject lateinit var weatherRepository: WeatherRepository

    init{
        MyApplication.instance.applicationComponent.inject(this)

    }

    private val cityData  = MutableStateFlow<CityData?>(CityData(1,"","-","-"))

    var uiState = cityData.asStateFlow()

    fun getWeather(city: String){
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.getWeather(city, cityData)
        }

    }
}