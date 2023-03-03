package com.daveloper.littlelemon.utils

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("\\b[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,}\\b")
    return emailRegex.matches(this)
}