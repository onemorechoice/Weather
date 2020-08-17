package com.yjf.weather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object WeatherNetwork {

    suspend fun searchPlaces(query:String)=ServiceCreator
        .create<PlaceService>()
        .searchPlaces(query)
        .await()

   suspend fun getDailyWeather(lng:String,lat:String)=ServiceCreator
                                            .create<WeatherService>()
                                            .getDailyWeather(lng,lat)
                                            .await()



    suspend fun getRealtimeWeather(lng:String,lat:String)=ServiceCreator
        .create<WeatherService>()
        .getRealtimeWeather(lng,lat)
        .await()

}