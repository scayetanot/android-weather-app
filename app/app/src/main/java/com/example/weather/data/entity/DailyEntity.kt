package com.example.weather.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DailyEntity (
    @SerializedName("data")
    var data: List<DailyDataEntity>
): Serializable