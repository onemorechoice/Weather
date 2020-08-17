package com.yjf.weather.logic.model

data class Weather(val realtime:RealtimeResponse.RealTime,val daily: DailyResponse.Daily) {
}