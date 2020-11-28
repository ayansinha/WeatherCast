package org.weathercast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.weathercast.R

class ActivityForecast: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}