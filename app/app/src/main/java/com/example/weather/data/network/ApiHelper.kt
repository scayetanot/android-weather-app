package com.example.weather.data.network

class ApiHelper(private val apiService: DarkSkyApi){
    suspend fun getForecast(latitude: Double, longitude: Double) = apiService.getForecast(latitude, longitude)
}