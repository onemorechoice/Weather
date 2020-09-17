package com.yjf.weather.logic.dao

import android.content.Context
import com.google.gson.Gson
import com.yjf.weather.WeatherApplication
import com.yjf.weather.logic.model.Place

object PlaceDao {

    fun savePlace(place:Place){
         sharePreference().edit().putString("place", Gson().toJson(place)).apply()
    }

    fun getSavePlace():Place{
        val placeJson= sharePreference().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved()= sharePreference().contains("place")



    private fun sharePreference()=WeatherApplication.context.getSharedPreferences("weather",Context.MODE_PRIVATE)
}