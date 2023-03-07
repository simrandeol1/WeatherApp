package com.example.weatherapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.entity.CityData

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather WHERE name LIKE :city")
    fun findByCity(city: String): CityData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cityData: CityData)
}