package com.daveloper.littlelemon.utils

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("\\b[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,}\\b")
    return emailRegex.matches(this)
}

fun String.isValidUrl(): Boolean {
    val urlPattern = "^(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ;,./?%&=]*)?\$".toRegex()
    return urlPattern.matches(this)
}