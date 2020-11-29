package org.weathercast.data.model

data class WeatherCurrentModel(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val co_ord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)