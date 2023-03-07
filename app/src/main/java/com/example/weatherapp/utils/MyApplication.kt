package com.example.weatherapp

import dagger.Component
import javax.inject.Singleton
import android.app.Application
import com.example.weatherapp.utils.DataSourceModule
import com.example.weatherapp.viewmodel.CityViewModel

@Singleton
@Component(modules = [DataSourceModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(viewModel: CityViewModel)
}

class MyApplication: Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        applicationComponent = DaggerApplicationComponent
            .builder()
            .dataSourceModule(DataSourceModule(this))
            .build()
    }
}