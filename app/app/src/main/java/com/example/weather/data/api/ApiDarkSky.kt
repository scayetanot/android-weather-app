package com.example.weather.data.api

import com.example.weather.data.entity.response.WeatherForeCastResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDarkSky {
    @GET("forecast/{secretid}/{latitude},{longitude}?/exclude=alerts,minutely")
    suspend fun getForecast(@Path("secretid") secretid: String,
                            @Path("latitude") latitude: Double,
                            @Path("longitude") longitude: Double): WeatherForeCastResponse
}