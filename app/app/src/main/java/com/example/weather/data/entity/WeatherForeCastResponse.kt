package com.example.weather.data.entity

data class WeatherForeCastResponse(
    var latitude : Double,
    var longitude : Double,
    var timezone: String,
    var currently: CurrentlyEntity,
    var minutely: MinutelyEntity,
    var hourly: HourlyEntity,
    var daily: DailyEntity
    //Alert is not necessary for now
    //var alerts: AlertsEntity,
)
