package com.example.weather.data_source

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weather.BuildConfig
import com.example.weather.data.ResultForeCast
import com.example.weather.data.api.ApiDarkSky
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.di.IoDispatcher
import com.example.weather.utils.convertToReadableDate
import com.example.weather.utils.convertToReadableDay
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime


class RemoteDataSourceImpl(
    private val api: ApiDarkSky,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ResultForeCast<ForeCast> =
        withContext(ioDispatcher) {
            val request =
                api.getForecast(BuildConfig.DARKSKY_SECRET_KEY, latitude, longitude)


            var listOfTodayTemp = request.hourly.data.filter {
                var test = convertToReadableDay(it.time).toInt()
                var test2 = LocalDate.now().dayOfMonth
                convertToReadableDay(it.time).toInt() == LocalDate.now().dayOfMonth
            }

          //  listOfTodayTemp.filter {
          //      var test = convertToReadableDay(it.time).toInt()
          //      var test2 = LocalDate.now().dayOfMonth
          //      convertToReadableDay(it.time).toInt() == LocalDate.now().dayOfMonth
          //  }

            ResultForeCast.Success(ForeCast(
                    "",
                    request.latitude,
                    request.longitude,
                    convertToReadableDate(request.currently.time),
                    request.currently.summary,
                    request.currently.icon,
                    request.currently.temperature,
                    getMinTemp(listOfTodayTemp),
                    getMaxTemp(listOfTodayTemp),
                listOfTodayTemp
            ))
        }

    private fun getMinTemp(listOfTemp: List<HourlyDataEntity>?): Float? {
        return listOfTemp?.minBy { it.temperature }?.temperature
    }

    private fun getMaxTemp(listOfTemp: List<HourlyDataEntity>): Float? {
        return listOfTemp?.maxBy { it.temperature }?.temperature
    }
}