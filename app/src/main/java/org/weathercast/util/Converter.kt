package org.weathercast.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Converter {

    /**
     * current day of the week
     */
    val currentDay: String get() = Calendar.DAY_OF_WEEK.toString()

    /**
     * value of user's current time
     */
    @SuppressLint("SimpleDateFormat")
    fun currentTime(format: String): String {
        return SimpleDateFormat("MM-dd HH:mm").format(Date())
    }

    /**
     * function to convert kelvin-to-celsius in integer
     */
    fun kelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }
}
