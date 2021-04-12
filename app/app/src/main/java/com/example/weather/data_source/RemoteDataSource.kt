package com.example.weather.data_source

import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.response.WeatherForeCastResponse

interface RemoteDataSource {
    suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<ForeCast>
}