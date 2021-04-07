package com.example.weather.repository

import com.example.weather.data.RemoteDataNotFoundException
import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.WeatherForeCastResponse
import com.example.weather.data_source.RemoteDataSource
import com.example.weather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher


class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse> {
        return when (val result = remoteDataSource.getForecast(latitude, longitude)) {
            is ResultForeCast.Success -> {
                val response = result.data
                ResultForeCast.Success(response)
            }
            is ResultForeCast.Error -> {
                ResultForeCast.Error(RemoteDataNotFoundException())
            }
        }
    }
}