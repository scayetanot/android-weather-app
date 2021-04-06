package com.example.weather.data.entity

data class CurrentlyEntity (
    var time: Long,
    var summary: String,
    var icon: String,
    var nearestStormDistance: Float,
    var precipIntensity: Float,
    var precipIntensityError: Float,
    var precipProbability: Float,
    var precipType: String,
    var temperature: Float,
    var apparentTemperature: Float,
    var dewPoint: Float,
    var humidity: Float,
    var pressure: Float,
    var windSpeed: Float,
    var windGust: Float,
    var windBearing: Float,
    var cloudCover: Float,
    var uvIndex: Int,
    var visibility: Float,
    var ozone: Float
)
