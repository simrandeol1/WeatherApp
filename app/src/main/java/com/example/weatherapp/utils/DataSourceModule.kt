package com.example.weatherapp.utils

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.network.APIClient
import com.example.weatherapp.network.APIInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule(applicationContext: Context){
    private var INSTANCE: AppDatabase
    private var applicationCtx = applicationContext

    init{
        INSTANCE = Room.databaseBuilder(applicationCtx , AppDatabase::class.java, "weather").build()
    }

    @Singleton
    @Provides
    fun getDatabase(): AppDatabase {
//        synchronized(AppDatabase::class.java) {
//            if (!::INSTANCE.isInitialized) {
//                INSTANCE = Room.databaseBuilder(applicationCtx , AppDatabase::class.java, "weather").allowMainThreadQueries().build()
//            }
//        }
        return INSTANCE
    }

    @Singleton
    @Provides
    fun getRetrofitClient(): APIInterface {
        return APIClient.getClient().create(APIInterface::class.java)
    }

    @Singleton
    @Provides
    fun getWeatherDAO() = INSTANCE.weatherDao()

}