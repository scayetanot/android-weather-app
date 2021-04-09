package com.example.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.MainApplication
import com.example.weather.R
import com.example.weather.utils.viewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val appComponents by lazy { MainApplication.appComponents }
    private val LOCATION_REQUEST_CODE = 666
    private val DFLT_LAT: Double = 33.942791
    private val DFLT_LONG: Double = -118.410042
    //private val defaultLocation:

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        initViews()
        initObservers()
        checkLocationPermission()
    }

    private fun getViewModel(): MainActivityViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private fun initViews() {
        getViewModel().getForeCast(DFLT_LAT, DFLT_LONG )
    }

    private fun initObservers() {

        getViewModel().forecastResponse.observe(this, Observer {
            getViewModel().getCityName(applicationContext, it.latitude, it.longitude)
            locationTime.text = it.dateTime
            locationSummary.text = it.summary
           //locationWeatherPic.setImageDrawable(getDrawable(it.summary.icon)
            locationCurrentTemperature.text = formatTemperature(it.currentTemp)
            locationMinTemperature.text = formatTemperature(it.minTemp) // + "\u2109"
            locationMaxTemperature.text = formatTemperature(it.maxTemp)  //+ "\u2109"
        })

        getViewModel().findCityResponse.observe(this, Observer {
            locationName.text = it
        })

        getViewModel().errorMessage.observe(this, Observer {
            Toast.makeText(this,"Connection Error",Toast.LENGTH_LONG).show();
        })
    }

    private fun formatTemperature(temperature: Float?): String {
        return temperature?.toInt().toString() + "\u2109"
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
        } else {
            requestLocation()
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.d("LOG", "User didn't allowed the location")
                } else {
                    requestLocation();

                }
            }
        }
    }

    private fun requestLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                getViewModel().getForeCast(location!!.latitude, location.longitude)
            }
    }


}