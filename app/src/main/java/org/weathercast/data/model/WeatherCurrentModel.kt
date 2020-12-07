package org.weathercast.data.model

import com.google.gson.annotations.SerializedName

/**
 * [WeatherCurrentModel]
 */
data class WeatherCurrentModel(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    @SerializedName("co_ord")
    val coordinate: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    data class Clouds(
        val all: Int
    )

    data class Coord(
        val lat: Double,
        val lon: Double
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double,
        @SerializedName("grnd_level")
        val groundLevel: Int,
        val humidity: Int,
        val pressure: Int,
        @SerializedName("sea_level")
        val seaLevel: Int,
        val temp: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        @SerializedName("temp_min")
        val tempMin: Double
    )

    data class Sys(
        val country: String,
        val sunrise: Int,
        val sunset: Int
    )

    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )

    data class Wind(
        val deg: Int,
        val speed: Double
    )
}
