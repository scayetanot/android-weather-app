package com.example.weather.data.entity.response

import android.text.format.DateFormat
import com.example.weather.data.entity.*
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
            latitude,
            longitude,
            convertToReadableDate(currently.time),
            currently.summary,
            "icon",
            currently.temperature.toString(),
            "00",
            "99",
            emptyList()
        )
    }

    private fun convertToReadableDate(timestamp: Long): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = timestamp * 1000
        return DateFormat.format("dd-MM-yyyy  HH:mm:ss", cal).toString()
    }
}
