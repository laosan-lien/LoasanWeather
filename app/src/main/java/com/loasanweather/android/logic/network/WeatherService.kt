package com.loasanweather.android.logic.network

import com.loasanweather.android.SunnyWeatherApplication
import com.loasanweather.android.logic.model.DailyResponse
import com.loasanweather.android.logic.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealTimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealTimeResponse>//@Path向接口中动态传入经纬度

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>//@Path向接口中动态传入经纬度

}