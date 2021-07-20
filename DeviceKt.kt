package com.example.appname

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.veloceapp.UtilitiesKt
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class DeviceKt {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getMAC(context: Context): String {
        val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = manager.connectionInfo
        println(info)
        return info.macAddress
    }

    fun askMACPermission(activity: Activity): Boolean {
        ActivityCompat.requestPermissions(activity, arrayOf("android.permission.ACCESS_WIFI_STATE"), UtilitiesKt().random(0..1000))
        if(this.hasMACPermission(activity)) {
            return true
        }
        return false
    }

    fun hasMACPermission(activity: Activity): Boolean {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    fun askCameraPermission(activity: Activity): Boolean {
        ActivityCompat.requestPermissions(activity, arrayOf("android.permission.CAMERA"), UtilitiesKt().random(0..1000))
        if(this.hasCameraPermission(activity)) {
            return true
        }
        return false
    }

    fun hasCameraPermission(activity: Activity): Boolean {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    fun askGeolocationPermission(activity: Activity): Boolean {
        ActivityCompat.requestPermissions(activity, arrayOf("android.permission.ACCESS_FINE_LOCATION"), UtilitiesKt().random(0..1000))
        if(this.hasGeolocationPermission(activity)) {
            return true
        }
        return false
    }

    @SuppressLint("MissingPermission")
    fun getGeolocation(context: Context): String {
        var latitude: String? = ""
        var longitude: String? = ""

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location->
            if(location != null) {
                latitude = location.latitude.toString()
                longitude = location.longitude.toString()
            }

        }
        return "$latitude,$longitude"
    }

    fun hasGeolocationPermission(activity: Activity): Boolean {
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    fun askStoragePermission(activity: Activity): Boolean {
        //ActivityCompat.requestPermissions(activity, arrayOf("android.permission.READ_EXTERNAL_STORAGE"), UtilitiesKt().random(0..1000)) //We don't need this one anymore since we didn't ask for it in the AndroidManifest
        ActivityCompat.requestPermissions(activity, arrayOf("android.permission.WRITE_EXTERNAL_STORAGE"), UtilitiesKt().random(0..1000))
        if(this.hasStoragePermission(activity)) {
            return true
        }
        return false
    }

    fun hasStoragePermission(activity: Activity): Boolean {
        if(//ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && //We don't need this one anymore since we didn't ask for it in the AndroidManifest
            ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }
}
