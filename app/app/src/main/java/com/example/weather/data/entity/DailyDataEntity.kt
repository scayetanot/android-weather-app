package com.example.weather.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DailyDataEntity (
    @SerializedName("time")
    var time: Long,
    @SerializedName("summary")
    var summary: String,
    @SerializedName("icon")
    var icon: String ,
    @SerializedName("sunriseTime")
    var sunriseTime: Long,
    @SerializedName("sunsetTime")
    var sunsetTime: Long,
    @SerializedName("moonPhase")
    var moonPhase: Float,
    @SerializedName("precipIntensity")
    var precipIntensity: Float,
    @SerializedName("precipIntensityMax")
    var precipIntensityMax: Float,
    @SerializedName("precipIntensityMaxTime")
    var precipIntensityMaxTime: Long,
    @SerializedName("precipProbability")
    var precipProbability: Float,
    @SerializedName("precipAccumulation")
    var precipAccumulation: Float,
    @SerializedName("precipType")
    var precipType: String ,
    @SerializedName("temperatureHigh")
    var temperatureHigh: Float,
    @SerializedName("temperatureHighTime")
    var temperatureHighTime: Long,
    @SerializedName("temperatureLow")
    var temperatureLow: Float,
    @SerializedName("temperatureLowTime")
    var temperatureLowTime: Long,
    @SerializedName("apparentTemperatureHigh")
    var apparentTemperatureHigh: Float,
    @SerializedName("apparentTemperatureHighTime")
    var apparentTemperatureHighTime: Long,
    @SerializedName("apparentTemperatureLow")
    var apparentTemperatureLow: Float,
    @SerializedName("apparentTemperatureLowTime")
    var apparentTemperatureLowTime: Long,
    @SerializedName("dewPoint")
    var dewPoint: Float,
    @SerializedName("humidity")
    var humidity: Float,
    @SerializedName("pressure")
    var pressure: Float,
    @SerializedName("windSpeed")
    var windSpeed: Float,
    @SerializedName("windBearing")
    var windBearing: Float,
    @SerializedName("cloudCover")
    var cloudCover: Float,
    @SerializedName("uvIndex")
    var uvIndex: Int,
    @SerializedName("uvIndexTime")
    var uvIndexTime: Long,
    @SerializedName("visibility")
    var visibility: Float,
    @SerializedName("temperatureMin")
    var temperatureMin: Float,
    @SerializedName("temperatureMinTime")
    var temperatureMinTime: Long,
    @SerializedName("temperatureMax")
    var temperatureMax: Float,
    @SerializedName("temperatureMaxTime")
    var temperatureMaxTime: Long,
    @SerializedName("apparentTemperatureMin")
    var apparentTemperatureMin: Float,
    @SerializedName("apparentTemperatureMinTime")
    var apparentTemperatureMinTime: Long,
    @SerializedName("apparentTemperatureMax")
    var apparentTemperatureMax: Float,
    @SerializedName("apparentTemperatureMaxTime")
    var apparentTemperatureMaxTime: Long
): Serializable