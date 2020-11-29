package org.weathercast.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {

    private val currentTime: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return dateFormat.format(Date())
        }

    private val timestamp: String
        @SuppressLint("SimpleDateFormat")
        get(){
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = formatter.parse(currentTime) as Date
            return date.time.toString().dropLast(3) //it is returning
        }
}