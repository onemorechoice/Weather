package com.yjf.weather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yjf.weather.logic.Repository
import com.yjf.weather.logic.model.Place

class PlaceViewModel:ViewModel() {
    private val searchLiveData= MutableLiveData<String>()

    val placeList=ArrayList<Place>()

    val placeLiveData: LiveData<Result<List<Place>>> = Transformations.switchMap(searchLiveData){
        query->Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value=query
    }


    fun savePlace(place: Place)=Repository.savePlace(place)

    fun getSavePlace()=Repository.getSavePlace()

    fun isPlaceSaved()=Repository.isPlaceSaved()



}