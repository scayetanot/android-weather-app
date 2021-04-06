package com.example.weather.data.entity

data class DailyEntity (
    var summary: String,
    var icon: String,
    var data: List<DailyDataEntity>
)
