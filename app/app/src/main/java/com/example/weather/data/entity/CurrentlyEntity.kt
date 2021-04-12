package com.example.weather.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrentlyEntity (
        @SerializedName("time")
        var time: Long,
        @SerializedName("summary")
        var summary: String,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("nearestStormDistance")
        var nearestStormDistance: Float,
        @SerializedName("precipIntensity")
        var precipIntensity: Float,
        @SerializedName("precipIntensityError")
        var precipIntensityError: Float,
        @SerializedName("precipProbability")
        var precipProbability: Float,
        @SerializedName("precipType")
        var precipType: String,
        @SerializedName("temperature")
        var temperature: Float,
        @SerializedName("apparentTemperature")
        var apparentTemperature: Float,
        @SerializedName("dewPoint")
        var dewPoint: Float,
        @SerializedName("humidity")
        var humidity: Float,
        @SerializedName("pressure")
        var pressure: Float,
        @SerializedName("windSpeed")
        var windSpeed: Float,
        @SerializedName("windGust")
        var windGust: Float,
        @SerializedName("windBearing")
        var windBearing: Float,
        @SerializedName("cloudCover")
        var cloudCover: Float,
        @SerializedName("uvIndex")
        var uvIndex: Int,
        @SerializedName("visibility")
        var visibility: Float,
        @SerializedName("ozone")
        var ozone: Float
) : Serializable
