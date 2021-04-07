package com.example.weather.repository

import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.WeatherForeCastResponse

interface AppRepository {
    suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse>
}