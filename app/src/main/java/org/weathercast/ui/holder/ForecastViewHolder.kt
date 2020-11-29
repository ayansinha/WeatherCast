package org.weathercast.ui.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.weathercast.R

class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var container: ConstraintLayout = itemView.findViewById(R.id.container)
    var days: TextView = itemView.findViewById(R.id.textViewDay)
    var temp: TextView = itemView.findViewById(R.id.textViewTemp)
    var maxTemp: TextView = itemView.findViewById(R.id.textViewMaxTemp)
    var minTemp: TextView = itemView.findViewById(R.id.textViewMinTemp)
    var image: ImageView = itemView.findViewById(R.id.imageViewForecast)

}
