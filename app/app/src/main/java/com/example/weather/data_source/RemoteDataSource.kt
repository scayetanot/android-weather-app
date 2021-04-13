package com.example.weather.data_source

import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.model.ForeCast

interface RemoteDataSource {
    suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<ForeCast>
}