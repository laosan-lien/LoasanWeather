package com.loasanweather.android.logic.model

import com.google.gson.annotations.SerializedName
import okhttp3.Response

/**
 * 响应数据模型
 */
data class RealTimeResponse(val status: String, val result: Result) {
    data class Result(val realTime: RealTime)

    data class RealTime(
        val skyCon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}
