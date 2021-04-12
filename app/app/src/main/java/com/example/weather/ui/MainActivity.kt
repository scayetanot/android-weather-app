package com.example.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.MainApplication
import com.example.weather.R
import com.example.weather.ui.fragment.HourlyTemperaturesFragment
import com.example.weather.utils.findDrawable
import com.example.weather.utils.formatTemperature
import com.example.weather.utils.replaceFragment
import com.example.weather.utils.viewModelProvider
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private val appComponents by lazy { MainApplication.appComponents }
    private val LOCATION_REQUEST_CODE = 666
    private val DFLT_LAT: Double = 33.942791
    private val DFLT_LONG: Double = -118.410042

    private var latitude: Double = DFLT_LAT
    private var longitude: Double = DFLT_LONG

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

        weatherLayout.setOnClickListener {
            if (savedInstanceState == null) {
                frame_layout_container.visibility = View.VISIBLE
                replaceFragment(HourlyTemperaturesFragment(), R.id.frame_layout_container)
            }
        }

    }

    private fun getViewModel(): MainActivityViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private fun initViews() {
        getViewModel().getForeCast(latitude, longitude )
    }

    private fun initObservers() {

        getViewModel().forecastResponse.observe(this, Observer {
            getViewModel().getCityName(applicationContext, it.latitude, it.longitude)
            locationTime.text = it.dateTime
            locationSummary.text = it.summary
            locationWeatherPic.setImageDrawable(findDrawable(applicationContext, it.icon))
            locationCurrentTemperature.text = formatTemperature(it.currentTemp)
            locationMinTemperature.text = formatTemperature(it.minTemp)
            locationMaxTemperature.text = formatTemperature(it.maxTemp)
        })

        getViewModel().findCityResponse.observe(this, Observer {
            locationName.text = it
        })

        getViewModel().errorMessage.observe(this, Observer {
            Toast.makeText(this,"Connection Error",Toast.LENGTH_LONG).show();
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
        //getFusedLocation()
        getRealLocation()
    }

    private fun getFusedLocation() {
        fusedLocationClient.lastLocation
                 .addOnSuccessListener { location : Location? ->
                     if(location != null ) {
                         latitude = location.latitude
                         longitude = location.longitude
                         getViewModel().getForeCast(latitude, longitude)
                     } else {
                         getRealLocation()
                     }
                 }
    }
    private fun getRealLocation(){
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        try {
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                    , 0L, 0f, locationListener)
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER
                    , 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.latitude
            longitude = location.longitude
            getViewModel().getForeCast(latitude, longitude)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}