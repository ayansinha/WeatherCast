package org.weathercast.data.model

import com.google.gson.annotations.SerializedName

/**
 * [WeeksForecastModel]
 */
data class WeeksForecastModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeeksData>,
    val message: Int
) {
    data class WeeksData(
        val clouds: Clouds,
        val dt: Int,
        @SerializedName("dt_txt")
        val dtText: String,
        val main: Main,
        val pop: Int,
        val sys: Sys,
        val visibility: Int,
        val weather: List<Weather>,
        val wind: Wind
    ) {
        data class Clouds(
            val all: Int
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
            @SerializedName("temp_kf")
            val tempKf: Double,
            @SerializedName("temp_max")
            val tempMax: Double,
            @SerializedName("temp_min")
            val tempMin: Double
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
        data class Sys(
            val pod: String
        )
    }
    data class City(
        val coord: Coord,
        val country: String,
        val id: Int,
        val name: String,
        val population: Int,
        val sunrise: Int,
        val sunset: Int,
        val timezone: Int
    ) {
        data class Coord(
            val lat: Double,
            val lon: Double
        )
    }
}
