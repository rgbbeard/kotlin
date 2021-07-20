package com.example.appname

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager


@Suppress("DEPRECATION")
class InternetKt {
    private val NETWORK_TYPE_EHRPD = 14 // Level 11
    private val NETWORK_TYPE_EVDO_B = 12 // Level 9
    private val NETWORK_TYPE_HSPAP = 15 // Level 13
    private val NETWORK_TYPE_IDEN = 11 // Level 8
    private val NETWORK_TYPE_LTE = 13 // Level 11

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun hasEnoughConnectivity(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo

        if(this.connectionSpeedLevel(info!!.getType(), info.subtype) > 1) {
            return true
        }
        return false
    }

    fun connectionSpeedLevel(type: Int, subType: Int): Int {
        val connectionSpeedLevel: Int

        connectionSpeedLevel = if (type == ConnectivityManager.TYPE_WIFI) {
            4
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> 2 // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_CDMA -> 1 // ~ 14-64 kbps
                TelephonyManager.NETWORK_TYPE_EDGE -> 2 // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> 3 // ~ 400-1000 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_A -> 3 // ~ 600-1400 kbps
                TelephonyManager.NETWORK_TYPE_GPRS -> 2 // ~ 100 kbps
                TelephonyManager.NETWORK_TYPE_HSDPA -> 4 // ~ 2-14 mbps
                TelephonyManager.NETWORK_TYPE_HSPA -> 3 // ~ 700-1700 kbps
                TelephonyManager.NETWORK_TYPE_HSUPA -> 4 // ~ 1-23 mbps
                TelephonyManager.NETWORK_TYPE_UMTS -> 3 // ~ 400-7000 kbps
                this.NETWORK_TYPE_EHRPD -> 4 // ~ 1-2 mbps
                this.NETWORK_TYPE_EVDO_B -> 4 // ~ 5 mbps
                this.NETWORK_TYPE_HSPAP -> 4 // ~ 10-20 mbps
                this.NETWORK_TYPE_IDEN -> 1 // ~ 25 kbps
                this.NETWORK_TYPE_LTE -> 4 // ~ 10+ mbps
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> 0
                else -> 0
            }
        } else {
            0
        }

        return connectionSpeedLevel
    }
}
