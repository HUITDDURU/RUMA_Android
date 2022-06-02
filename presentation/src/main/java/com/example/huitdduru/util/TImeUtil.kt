package com.example.huitdduru.util

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
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

@SuppressLint("SimpleDateFormat")
fun getDate(date: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formatDate = dateFormat.parse(date)
    return dateFormat.format(formatDate!!)
}

@SuppressLint("SetTextI18n")
@RequiresApi(Build.VERSION_CODES.M)
fun datePicker(et: EditText, context: Context){
    val calendar = Calendar.getInstance()
    val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        et.setText("${year}-${month+1}-${dayOfMonth}")
    }
    DatePickerDialog(context,
        listener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}