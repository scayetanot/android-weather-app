package com.example.weather.di.component

import android.content.Context
import com.example.weather.data_source.LocalDataSource
import com.example.weather.data_source.LocalDataSourceImpl
import com.example.weather.di.modules.*
import com.example.weather.ui.MainActivity
import com.example.weather.ui.fragment.HourlyTemperaturesFragment
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class,
        StorageModule::class
    ]
)

interface AppComponents {
    fun context(): Context

    fun retrofit(): Retrofit

    fun appDataObject(): LocalDataSourceImpl

    fun appDataBase(): LocalDataSource

    fun inject(mainActivity: MainActivity)
    fun inject(hourlyTemperaturesFragment: HourlyTemperaturesFragment)
}