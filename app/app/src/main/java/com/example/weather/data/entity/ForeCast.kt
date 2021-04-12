package com.example.weather.data.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "ForeCast", primaryKeys = ["city"])
data class ForeCast(
        @SerializedName("city")
        var city: String,
        @SerializedName("latitude")
        var latitude: Double,
        @SerializedName("longitude")
        var longitude: Double,
        @SerializedName("dateTime")
        var dateTime: String,
        @SerializedName("summary")
        var summary: String,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("currentTemp")
        var currentTemp: Float?,
        @SerializedName("minTemp")
        var minTemp: Float?,
        @SerializedName("maxTemp")
        var maxTemp: Float?,
        @SerializedName("hourlyDetails")
        var hourlyDetails: List<HourlyDataEntity>
) : Serializable