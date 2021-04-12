package com.example.weather.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.data.entity.response.WeatherForeCastResponse

@Dao
interface LocalDataSourceImpl {

    @Query("SELECT * FROM ForeCast")
    suspend fun getHourlyTemperature(): ForeCast

    @Query("SELECT * FROM ForeCast")
    suspend fun getForecast(): ForeCast

    @Insert(onConflict = REPLACE)
    suspend fun setForecast(foreCast: ForeCast?)

}