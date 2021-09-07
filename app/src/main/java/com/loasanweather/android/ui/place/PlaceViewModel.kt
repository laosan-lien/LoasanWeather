package com.loasanweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasanweather.android.logic.Repository
import com.loasanweather.android.logic.dao.PlaceDao
import com.loasanweather.android.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    private val TAG = "PlaceViewModel"
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavePlace() = Repository.getSavePlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}