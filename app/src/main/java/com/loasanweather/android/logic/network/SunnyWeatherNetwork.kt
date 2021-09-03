package com.loasanweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * 统一数据源的访问入口
 */
class SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)//创建一个动态代理对象

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealTimeWeather(lng: String, lat: String) =
        weatherService.getRealTimeWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    //TODO("Not yet implemented")
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t);

                }

            })
        }
    }

}