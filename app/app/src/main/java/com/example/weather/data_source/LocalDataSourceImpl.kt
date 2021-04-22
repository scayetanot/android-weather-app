package com.example.weather.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weather.data.entity.model.ForeCast
import java.util.*

@Dao
interface LocalDataSourceImpl {

    @Query("SELECT * FROM ForeCast WHERE uuid = :uuid LIMIT 1")
    suspend fun getHourlyTemperature(uuid: String): ForeCast

    @Query("SELECT * FROM ForeCast")
    suspend fun getForecast(): ForeCast

    @Insert(onConflict = REPLACE)
    suspend fun setForecast(foreCast: ForeCast?)

    @Query("DELETE FROM ForeCast")
    suspend fun deleteAllForecast()

}