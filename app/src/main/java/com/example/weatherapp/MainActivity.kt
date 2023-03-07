package com.example.weatherapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.viewmodel.CityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var cityViewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        MyApplication.instance.applicationComponent.inject(this)

        super.onCreate(savedInstanceState)

        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        setContent{
            CityCard(cityViewModel)
        }

    }

     private fun getWeather(city: String){
        cityViewModel.getWeather(city)
    }

    @Composable
    fun CityCard(cityViewModel: CityViewModel){

        val cityUiState by cityViewModel.uiState.collectAsState()

        Column(modifier = Modifier.padding(all = 8.dp)) {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Button(onClick = {
                    getWeather("Delhi")
                }) {
                    Text(text = "Delhi")
                }
                Button(onClick = {
                    getWeather("Mumbai")
                }) {
                    Text(text = "Mumbai")
                }
                Button(onClick = {
                    getWeather("Kolkata")
                }) {
                    Text(text = "Kolkata")
                }
                Button(onClick = {
                    getWeather("Pune")
                }) {
                    Text(text = "Pune")
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier.padding(all = 8.dp)) {
                Text(modifier = Modifier.weight(1f).padding(8.dp), color = Color.White, text = "Temperature: "+cityUiState?.currentTemp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(modifier = Modifier.weight(1f).padding(8.dp), color = Color.White, text = "Humidity: "+cityUiState?.humidity)
            }
        }
    }
}