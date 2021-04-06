package com.example.weather.data.network

import com.example.weather.data.entity.WeatherForeCastResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyApi {
    @GET("/{latitude},{longitude}")
    suspend fun getForecast(@Path("latitude") latitude: Double, @Path("longitude") longitude: Double): WeatherForeCastResponse
}