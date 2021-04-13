package com.example.weather.repository

import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.model.ForeCast
import com.example.weather.data.entity.HourlyDataEntity
import java.util.*

interface AppRepository {
    suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<ForeCast>
    suspend fun getForecastFromApi(latitude: Double, longitude: Double): ResultForeCast<ForeCast>
    suspend fun getForecastFromDb(): ResultForeCast<ForeCast>

    suspend fun getDetailsForHourlyForecast(uuid: String): ResultForeCast<List<HourlyDataEntity>>
}