package com.example.weather.data.entity

class HourlyEntity (
    var summary: String,
    var icon: String,
    var data: MutableList<HourlyDataEntity>
)