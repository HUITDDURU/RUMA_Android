package com.example.huitdduru.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private fun currentTime(): Date {
    val now = System.currentTimeMillis()
    return Date(now)
}

@SuppressLint("SimpleDateFormat")
fun getYear(): String =
    SimpleDateFormat("yyyy").format(currentTime())

@SuppressLint("SimpleDateFormat")
fun getMonth(): String =
    SimpleDateFormat("MM").format(currentTime())

@SuppressLint("SimpleDateFormat")
fun getDay(): String =
    SimpleDateFormat("dd").format(currentTime())

@SuppressLint("SimpleDateFormat")
fun getDate(): String =
    SimpleDateFormat("yyyy-MM-dd").format(currentTime())