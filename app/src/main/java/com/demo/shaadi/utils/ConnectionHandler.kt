package com.demo.shaadi.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectionHandler(private val context: Context) {

        fun isOnline(): Boolean {

            val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo=connectivityManager.activeNetworkInfo

            return  networkInfo!=null && networkInfo.isConnected
        }
}