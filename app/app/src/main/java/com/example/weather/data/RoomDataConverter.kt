package com.example.weather.data

import androidx.room.TypeConverter
import com.example.weather.data.entity.HourlyDataEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class RoomDataConverter : Serializable {

    @TypeConverter
    fun stringFromObject(list: HourlyDataEntity?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun getObjectFromString(jsonString: String?): HourlyDataEntity? {
        val listType: Type = object : TypeToken<HourlyDataEntity?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


    @TypeConverter
    fun stringFromListObject(list: List<HourlyDataEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun getListObjectFromString(jsonString: String?): List<HourlyDataEntity?>? {
        val listType: Type = object : TypeToken<ArrayList<HourlyDataEntity?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}