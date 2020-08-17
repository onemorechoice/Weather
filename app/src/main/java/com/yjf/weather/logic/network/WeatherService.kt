package com.yjf.weather.logic.network

import android.app.Application
import com.yjf.weather.WeatherApplication
import com.yjf.weather.logic.model.DailyResponse
import com.yjf.weather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng:String,@Path("lat")lat:String): Call<RealtimeResponse>


    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng:String,@Path("lat")lat:String):Call<DailyResponse>

}