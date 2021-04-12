package com.example.weather.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HourlyEntity (
        @SerializedName("summary")
        var summary: String,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("data")
        var data: MutableList<HourlyDataEntity>
) : Serializable