package com.loasanweather.android.logic.model

import com.google.gson.annotations.SerializedName


/**
 * 根据返回json数据结构生成响应类
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String, val location: Location,
    // 由于JSON字段和kotlin字段的命名规范不太一致，@SerializedName注解是来让JSON字段和kotlin字段之间建立映射关系
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)


