package com.yjf.weather

import android.app.Application
import android.content.Context

class WeatherApplication:Application() {

    companion object{
        lateinit var context: Context
        const val TOKEN="dRFXa10sLnwLydoj"
    }


    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }

}