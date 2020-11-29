package org.weathercast.data.model

data class ForecastModel(
    val days: String,
    val temp: String,
    val maxTemp: String,
    val minTemp: String,
    val image: Int
)
