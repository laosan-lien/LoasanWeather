package com.loasanweather.android.logic


import androidx.lifecycle.liveData
import com.loasanweather.android.logic.model.Place
import com.loasanweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

/**
 *仓库层的同意封装入口
 */

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try{
            val placeResponse = SunnyWeatherNetwork().searchPlaces(query)//SunnyWeatherNetwork()的括号是否可以省略
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        }catch (e :Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
 }