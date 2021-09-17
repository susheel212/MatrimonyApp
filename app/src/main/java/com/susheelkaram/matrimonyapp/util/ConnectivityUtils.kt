package com.susheelkaram.matrimonyapp.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class ConnectivityUtils {
    companion object {
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val activeNetworkInfo = connectivityManager?.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }
}