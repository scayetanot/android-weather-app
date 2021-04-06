package com.example.weather

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val LOCATION_REQUEST_CODE = 666

    private lateinit var tempTextField: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempTextField = findViewById(R.id.tmpTextView)

        checkLocationPermission()
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
                    tempTextField.text = "Location has been refused, So let's show Los Angeles"
                } else {
                    tempTextField.text = "Location has been granted, So let's show the user location"
                }
            }
        }
    }

}