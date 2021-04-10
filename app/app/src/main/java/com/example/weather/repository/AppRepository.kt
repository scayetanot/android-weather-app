package com.example.weather.repository

import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.data.entity.response.WeatherForeCastResponse

interface AppRepository {
    suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse>
    suspend fun getForecastFromApi(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse>
    suspend fun getForecastFromDb(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse>

    suspend fun getDetailsForHourlyForecast(): ResultForeCast<List<HourlyDataEntity>>
}