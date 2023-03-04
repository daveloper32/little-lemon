package com.daveloper.littlelemon.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("\\b[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,}\\b")
    return emailRegex.matches(this)
}

fun String.isValidUrl(): Boolean {
    val urlPattern = "^(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ;,./?%&=]*)?\$".toRegex()
    return urlPattern.matches(this)
}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}