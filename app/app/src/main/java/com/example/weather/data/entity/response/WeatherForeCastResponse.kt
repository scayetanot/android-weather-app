package com.example.weather.data.entity.response

import com.example.weather.data.entity.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherForeCastResponse(
        @SerializedName("latitude")
        var latitude : Double,
        @SerializedName("longitude")
        var longitude : Double,
        @SerializedName("timezone")
        var timezone: String,
        @SerializedName("currently")
        var currently: CurrentlyEntity,
        @SerializedName("hourly")
        var hourly: HourlyEntity
): Serializable

