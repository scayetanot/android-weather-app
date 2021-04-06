package com.example.weather.data.entity

data class MinutelyDataEntity (
    var time: Long,
    var precipIntensity: Float,
    var precipIntensityError: Float,
    var precipProbability: Float,
    var precipType: String
)