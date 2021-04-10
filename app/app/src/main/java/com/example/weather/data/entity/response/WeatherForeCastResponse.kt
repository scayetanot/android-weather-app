package com.example.weather.data.entity.response

import android.text.format.DateFormat
import com.example.weather.data.entity.*
import com.example.weather.utils.convertToReadableDate
import java.util.*

data class WeatherForeCastResponse(
    var latitude : Double,
    var longitude : Double,
    var timezone: String,
    var currently: CurrentlyEntity,
   // var minutely: MinutelyEntity,
    var hourly: HourlyEntity
 //   var daily: DailyEntity
    //Alert is not necessary for now
    //var alerts: AlertsEntity,
) {
    fun mapToForeCast(): ForeCast {
        return ForeCast(
                "",
                latitude,
                longitude,
                convertToReadableDate(currently.time),
                currently.summary,
                "icon",
                currently.temperature,
                getMinTemp(hourly.data),
                getMaxTemp(hourly.data),
                hourly.data
        )
    }



    private fun getMinTemp(listOfTemp: List<HourlyDataEntity>?): Float? {
        return listOfTemp?.minBy { it.temperature }?.temperature
    }

    private fun getMaxTemp(listOfTemp: List<HourlyDataEntity>): Float? {
        return listOfTemp?.maxBy { it.temperature }?.temperature
    }
}

