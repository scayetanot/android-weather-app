package com.example.weather.data.entity

data class ForeCast(
    var city: String,
    var latitude: Double,
    var longitude: Double,
    var dateTime: String,
    var summary: String,
    var icon: String,
    var currentTemp: Float?,
    var minTemp: Float?,
    var maxTemp: Float?,
    var hourlyDetails: List<HourlyDataEntity>
)