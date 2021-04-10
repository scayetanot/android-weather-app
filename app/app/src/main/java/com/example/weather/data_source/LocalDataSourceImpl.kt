package com.example.weather.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.data.entity.response.WeatherForeCastResponse

@Dao
interface LocalDataSourceImpl {

    @Query("SELECT * FROM HourlyDataEntity")
    suspend fun getHourlyTemperature(): List<HourlyDataEntity>

    @Insert(onConflict = REPLACE)
    suspend fun setHourlyTemperature(listHourlyTemperature: List<HourlyDataEntity?>)

    @Query("SELECT * FROM WeatherForeCastResponse")
    suspend fun getForecast(): WeatherForeCastResponse

    @Insert(onConflict = REPLACE)
    suspend fun setForecast(foreCast: WeatherForeCastResponse?)

}