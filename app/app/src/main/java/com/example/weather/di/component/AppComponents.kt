package com.example.weather.di.component

import android.content.Context
import com.example.weather.di.modules.AppModule
import com.example.weather.di.modules.CoroutinesModule
import com.example.weather.di.modules.NetworkModule
import com.example.weather.di.modules.RepositoryModule
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
        CoroutinesModule::class
    ]
)

interface AppComponents {
    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
    fun inject(hourlyTemperaturesFragment: HourlyTemperaturesFragment)
}