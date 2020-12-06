package org.weathercast.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import org.weathercast.R
import org.weathercast.data.model.ForecastModel
import org.weathercast.data.model.WeeksForecastModel
import org.weathercast.ui.holder.ForecastViewHolder
import org.weathercast.util.Converter

class ForecastAdapter(private var weekList: List<WeeksForecastModel.WeeksData>, private var context: Context): RecyclerView.Adapter<ForecastViewHolder>() {
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder = ForecastViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_multiple_days , parent , false))

    override fun getItemCount(): Int = weekList.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast: WeeksForecastModel.WeeksData = weekList[position]
        holder.container.animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_rise_up
        )
        holder.days.text = Converter.currentTime(forecast.dt_txt)
        holder.temp.text = Converter.kelvinToCelsius(forecast.main.temp).toString()
        holder.maxTemp.text = Converter.kelvinToCelsius(forecast.main.temp_max).toString()
        holder.minTemp.text = Converter.kelvinToCelsius(forecast.main.temp_min).toString()
        when(forecast.weather[0].main) {
            "Clouds", "Snow" ->  { holder.image.setImageResource(R.drawable.ic_cloud) }
            "Rain", "Drizzle" -> { holder.image.setImageResource(R.drawable.ic_rainy) }
            "Clear", "Atmosphere" -> { holder.image.setImageResource(R.drawable.ic_sunny) }
            "Thunderstorm" -> { holder.image.setImageResource(R.drawable.ic_thunder) }
        }
    }
}
