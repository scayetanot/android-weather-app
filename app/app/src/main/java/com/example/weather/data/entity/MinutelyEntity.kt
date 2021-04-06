package com.example.weather.data.entity

data class MinutelyEntity (
    var summary: String,
    var icon: String,
    var data: List<MinutelyDataEntity>
)