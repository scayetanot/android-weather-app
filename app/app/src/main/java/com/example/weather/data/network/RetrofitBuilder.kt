package com.example.weather.data.network

import com.example.weather.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        val darkSkyUrl = BuildConfig.DARKSKY_URL + BuildConfig.DARKSKY_SECRET_KEY + "/"
        return Retrofit.Builder()
            .baseUrl(darkSkyUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiDarkSky: DarkSkyApi = getRetrofit().create(DarkSkyApi::class.java)
}