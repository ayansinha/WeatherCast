package org.weathercast.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import org.weathercast.R
import org.weathercast.data.model.ForecastModel
import org.weathercast.ui.holder.ForecastViewHolder

class ForecastAdapter(private var list: MutableList<ForecastModel>, private var context: Context): RecyclerView.Adapter<ForecastViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {

        return ForecastViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_multiple_days , parent , false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {


        val forecast: ForecastModel = list[position]
        holder.container.animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_rise_up
        )

        holder.days.text = forecast.days
        holder.temp.text = forecast.temp
        holder.maxTemp.text = forecast.maxTemp
        holder.minTemp.text = forecast.minTemp
        holder.image.setImageResource(forecast.image)

        //android:background="@drawable/card_border"
        if (selectedPosition == position){
            holder.container.setBackgroundResource(R.drawable.card_border)
        }
        holder.container.setBackgroundResource(R.drawable.background_selector)



    }

}