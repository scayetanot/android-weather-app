package com.example.weather

import android.app.Application
import com.example.weather.di.component.AppComponents
import com.example.weather.di.component.DaggerAppComponents
import com.example.weather.di.modules.AppModule
import com.example.weather.utils.InternetUtil

open class MainApplication : Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
        InternetUtil.init(this)
    }

    private fun initDagger(app: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .build()
}