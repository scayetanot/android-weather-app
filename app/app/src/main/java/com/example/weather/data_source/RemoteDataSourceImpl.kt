package com.example.weather.data_source

import com.example.weather.BuildConfig
import com.example.weather.data.ResultForeCast
import com.example.weather.data.api.ApiDarkSky
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.data.entity.response.WeatherForeCastResponse
import com.example.weather.di.IoDispatcher
import com.example.weather.utils.convertToReadableDate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ApiDarkSky,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ResultForeCast<ForeCast> =
        withContext(ioDispatcher) {
            val request =
                api.getForecast(BuildConfig.DARKSKY_SECRET_KEY, latitude, longitude)
            ResultForeCast.Success(ForeCast(
                    "",
                    request.latitude,
                    request.longitude,
                    convertToReadableDate(request.currently.time),
                    request.currently.summary,
                    request.currently.icon,
                    request.currently.temperature,
                    getMinTemp(request.hourly.data),
                    getMaxTemp(request.hourly.data),
                    request.hourly.data
            ))
        }

    private fun getMinTemp(listOfTemp: List<HourlyDataEntity>?): Float? {
        return listOfTemp?.minBy { it.temperature }?.temperature
    }

    private fun getMaxTemp(listOfTemp: List<HourlyDataEntity>): Float? {
        return listOfTemp?.maxBy { it.temperature }?.temperature
    }
}