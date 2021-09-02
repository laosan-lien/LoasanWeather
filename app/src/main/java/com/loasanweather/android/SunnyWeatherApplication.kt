package com.loasanweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 提供一种全局获取context的方式
 */
class SunnyWeatherApplication :Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context :Context
        const val TOKEN = "请输入你的令牌"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}