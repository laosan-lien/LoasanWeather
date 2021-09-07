package com.loasanweather.android.logic.model

class Weather {
    data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
}