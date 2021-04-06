package com.example.weather.data.network

class ApiHelper(private val apiDarkSky: DarkSkyApi){
    suspend fun getForecast(latitude: Double, longitude: Double) = apiDarkSky.getForecast(latitude, longitude)
}