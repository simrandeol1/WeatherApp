package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "weather")
data class CityData(
    @PrimaryKey(autoGenerate = true) var cityId: Long?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "currenttemp") var currentTemp: String,
    @ColumnInfo(name = "humidity") var humidity: String
) : Serializable