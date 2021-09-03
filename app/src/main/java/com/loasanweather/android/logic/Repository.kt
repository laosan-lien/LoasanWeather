package com.loasanweather.android.logic


import androidx.lifecycle.liveData
import com.loasanweather.android.logic.model.Weather
import com.loasanweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 *仓库层的同意封装入口
 */

object Repository {
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse =
            SunnyWeatherNetwork().searchPlaces(query)//SunnyWeatherNetwork()的括号是否可以省略
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealTime = async {
                SunnyWeatherNetwork().getRealTimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork().getDailyWeather(lng, lat)
            }
            val realTimeResponse = deferredRealTime.await()//await（）方法是为了保证两个网络请求都能够执行完成
            val dailyResponse = deferredDaily.await()
            if (realTimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realTimeResponse.result.realTime, dailyResponse.result.daily)
                Result.success(weather);
            } else {
                Result.failure(
                    RuntimeException(
                        "Realtime response status is${realTimeResponse.status}"
                                + "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }

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