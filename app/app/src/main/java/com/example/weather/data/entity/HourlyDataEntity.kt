package com.example.weather.data.entity

class HourlyDataEntity (
    var time: Long,
    var summary: String,
    var icon: String,
    var precipIntensity: Float,
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