package com.example.weather.data.entity

data class ForeCast(
    var latitude: Double,
    var longitude: Double,
    var dateTime: String,
    var summary: String,
    var icon: String,
    var currentTemp: String,
    var minTemp: String,
    var maxTemp: String,
    var hourlyDetails: List<HourlyTemp>
)