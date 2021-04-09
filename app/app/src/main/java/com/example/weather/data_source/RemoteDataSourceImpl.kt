package com.example.weather.data_source

import com.example.weather.BuildConfig
import com.example.weather.data.ResultForeCast
import com.example.weather.data.api.ApiDarkSky
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.response.WeatherForeCastResponse
import com.example.weather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ApiDarkSky,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ResultForeCast<WeatherForeCastResponse> =
        withContext(ioDispatcher) {
            val request =
                api.getForecast(BuildConfig.DARKSKY_SECRET_KEY, latitude, longitude)
            ResultForeCast.Success(request)
        }
}