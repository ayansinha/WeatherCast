package org.weathercast.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Converter {

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

    /**
     * TODO -> keep it in a util class to convert for all the temperatures
     */
    fun kelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }
}