package org.weathercast.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.weathercast.R
import org.weathercast.data.model.ForecastModel
import org.weathercast.ui.adapter.ForecastAdapter

class ActivityForecast: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastLists = mutableListOf(
            ForecastModel("Monday", "31\u00B0c" , "34°" , "28°" , R.drawable.ic_cloud),
            ForecastModel("Tuesday", "29\u00B0c" , "33°" , "27°" , R.drawable.ic_sunny),
            ForecastModel("Wednesday", "32\u00B0c" , "35°" , "26°", R.drawable.ic_rainy),
            ForecastModel("Thursday", "33\u00B0c" , "36°" , "28°", R.drawable.ic_night),
            ForecastModel("Friday", "31\u00B0c", "35°", "29°", R.drawable.ic_thunder)
        )


        recyclerViewForecast.apply {
            layoutManager = LinearLayoutManager(this@ActivityForecast , RecyclerView.HORIZONTAL , false)
            setHasFixedSize(true)
            adapter = ForecastAdapter(forecastLists , this@ActivityForecast)

        }
    }
}