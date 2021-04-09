package com.example.weather.ui

import androidx.lifecycle.LiveData
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.ForeCast
import com.example.weather.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    val forecastResponse = MutableLiveData<ForeCast>()

   // val findCityResponse = MutableLiveData<String>()

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage


    fun getForeCast(lat: Double, lon: Double){
        viewModelScope.launch {
            try {
               when (val response = repositoryImpl.getForecast(lat, lon)){
                    is ResultForeCast.Success -> {
                        var foreCast: ForeCast = response.data.mapToForeCast()
                      /*  foreCast.city = getCityName(foreCast.latitude, foreCast.longitude) */
                        forecastResponse.postValue(foreCast)
                    }
                    is ResultForeCast.Error -> {
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

 //   fun getCityName(lat: Double, lon: Double){
  //      viewModelScope.launch {
  //          try {
  //              var gcd = Geocoder(context, Locale.getDefault())
  //              var addr: List<Address>
  //              var cityName: String = "Unknown city"

   //             addresses = gcd.getFromLocation(lat, lon, 1)
   //             cityName = "test"
   //             findCityResponse.postValue(cityName)
   //         } catch {
   //             (e: Exception) {
   //                 _errorMessage.postValue(e.message)
   //             }
   //         }
   //     }
   // }
}