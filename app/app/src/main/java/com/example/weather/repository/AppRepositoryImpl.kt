package com.example.weather.repository

import com.example.weather.data.RemoteDataNotFoundException
import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.data.entity.response.WeatherForeCastResponse
import com.example.weather.data_source.LocalDataSource
import com.example.weather.data_source.LocalDataSourceImpl
import com.example.weather.data_source.RemoteDataSource
import com.example.weather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.example.weather.utils.InternetUtil


class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSourceImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    private val isInternetOn = InternetUtil.isInternetOn()

    override suspend fun getForecastFromApi(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse> {
        return when (val result = remoteDataSource.getForecast(latitude, longitude)) {
            is ResultForeCast.Success -> {
                val response = result.data
                withContext(ioDispatcher) {
                    localDataSource.setForecast(response)
                    localDataSource.setHourlyTemperature(response.hourly.data)
                }
                ResultForeCast.Success(response)
            }
            is ResultForeCast.Error -> {
                ResultForeCast.Error(RemoteDataNotFoundException())
            }
        }
    }

    override suspend fun getForecastFromDb(
        latitude: Double,
        longitude: Double
    ): ResultForeCast<WeatherForeCastResponse> =
        withContext(ioDispatcher) {
            ResultForeCast.Success(localDataSource.getForecast(latitude, longitude))
        }

    override suspend fun getForecast(latitude: Double, longitude: Double): ResultForeCast<WeatherForeCastResponse> {
        return if (isInternetOn) {
            getForecastFromApi(latitude, longitude)
        } else {
            getForecastFromDb(latitude, longitude)
        }
    }

    override suspend fun getDetailsForHourlyForecast(): ResultForeCast<List<HourlyDataEntity>> =
        withContext(ioDispatcher){
            ResultForeCast.Success(localDataSource.getHourlyTemperature())
        }
}