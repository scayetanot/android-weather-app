package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.ForeCast
import com.example.weather.data.entity.response.WeatherForeCastResponse
import com.example.weather.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    val forecastResponse = MutableLiveData<ForeCast>()
    val findCity = String

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage


    fun getForeCast(lat: Double, lon: Double){
        viewModelScope.launch {
            try {
               when (val response = repositoryImpl.getForecast(lat, lon)){
                    is ResultForeCast.Success -> {
                        forecastResponse.postValue(response.data.mapToForeCast())
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
}