package com.yjf.weather.logic

import androidx.lifecycle.liveData
import com.yjf.weather.logic.model.Place
import com.yjf.weather.logic.model.Weather
import com.yjf.weather.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query:String)= fire(Dispatchers.IO){
        val placeResponse=WeatherNetwork.searchPlaces(query)
        if (placeResponse.status=="ok"){
            val places=placeResponse.places
            Result.success(places)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime=async { WeatherNetwork.getRealtimeWeather(lng, lat) }

            val deferredDaily=async { WeatherNetwork.getDailyWeather(lng, lat) }

            val realtimeResponse=deferredRealtime.await()

            val dailyResponse=deferredDaily.await()
            if (realtimeResponse.status=="ok"&&dailyResponse.status=="ok"){
                val weather=Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
               Result.failure(RuntimeException("realtime response"))
            }
        }
    }

    fun searchPlaces1(query:String) = liveData<Result<List<Place>>>(Dispatchers.IO){
        val result=try {
            val placeResponse=WeatherNetwork.searchPlaces(query)
            if (placeResponse.status=="ok"){
                val places=placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
             Result.failure<List<Place>>(e)
        }
        emit(result)
    }


    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}