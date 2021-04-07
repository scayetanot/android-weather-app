package com.example.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.MainApplication
import com.example.weather.R
import com.example.weather.databinding.ActivityMainWeatherBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val appComponents by lazy { MainApplication.appComponents }
    private val LOCATION_REQUEST_CODE = 666

    private lateinit var binding: ActivityMainWeatherBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_weather)

        initViews()
        initObservers()
        checkLocationPermission()
    }

    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun initViews() {
        getViewModel().getForeCast()
    }

    private fun initObservers() {

        getViewModel().forecastResponse.observe(this, Observer {
            binding.locationNameValue = "test city"
            binding.locationTimeValue = "date"
            binding.locationWeatherImageValue = it.daily.icon
            binding.locationCurrentTemperatureValue = it.currently.temperature.toString()
            binding.locationMinTemperatureValue = it.currently.temperature.toString()
            binding.locationMaxTemperatureValue = it.currently.temperature.toString()
        })

        getViewModel().errorMessage.observe(this, Observer {
            //showErrorDialog()
            Log.e("ERROR", "ERROR")
        })
    }

    private fun checkLocationPermission() {

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                showWhyLocationIsNeededAndRequestPermission()
            } else {
                requestPermission()

            }
        }
    }

    private fun showWhyLocationIsNeededAndRequestPermission() {
        val dialogBox = AlertDialog.Builder(this)
        dialogBox.setMessage(getString(R.string.location_req_desc))
            .setTitle(R.string.location_req_title)
        dialogBox.setPositiveButton(R.string.ok) { _, _ ->
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //binding.testForText = "Location has been refused, So let's show Los Angeles"
                } else {
                    //binding.testForText  = "Location has been granted, So let's show the user location"
                }
            }
        }
    }


}