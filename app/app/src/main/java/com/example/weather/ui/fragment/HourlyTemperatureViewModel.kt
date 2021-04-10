package com.example.weather.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.ResultForeCast
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class HourlyTemperatureViewModel @Inject constructor(
        private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _resultHourlyTemperature = MutableLiveData<List<HourlyDataEntity>>()
    var resultHourlyTemperature: LiveData<List<HourlyDataEntity>> = _resultHourlyTemperature

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    fun getHourlyTemperatures(lat: Double, lon: Double){
        viewModelScope.launch {
            try{
                when(val response = repositoryImpl.getForecast(lat, lon)){
                    is ResultForeCast.Success -> {
                        _resultHourlyTemperature.postValue(response.data.mapToForeCast().hourlyDetails)
                    }
                    is ResultForeCast.Error -> {
                        _errorMessage.postValue(response.exception.toString())
                    }
                }

            } catch (e: java.lang.Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

}

