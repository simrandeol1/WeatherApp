package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.entity.CityData
import com.example.weatherapp.dao.WeatherDao

@Database(entities = [CityData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}