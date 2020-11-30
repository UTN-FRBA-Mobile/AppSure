package com.utn.appsure.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.utn.appsure.R
import com.utn.appsure.adapter.PolicyAdapter
import com.utn.appsure.model.Policy

class MainActivity : AppCompatActivity() {
    companion object {

        private val TAG = "LocationProvider"

        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()

        if (!checkPermissions())
            startLocationPermissionRequest()
    }

    fun checkPermissions(): Boolean {
        val coarsePermision = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        val fineLocationPermision = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)

        val backgroundLocationPermision = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        return coarsePermision == PackageManager.PERMISSION_GRANTED
                && fineLocationPermision == PackageManager.PERMISSION_GRANTED
                && backgroundLocationPermision == PackageManager.PERMISSION_GRANTED
    }


    fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE)
    }
}