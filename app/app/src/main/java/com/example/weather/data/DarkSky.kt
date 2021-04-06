package com.example.weather.data

import com.example.weather.data.network.ApiHelper

class DarkSky(private val apiHelper: ApiHelper) {
    suspend fun getForeCast(latitude: Double, longitude: Double) =
        apiHelper.getForecast(latitude, longitude)
}