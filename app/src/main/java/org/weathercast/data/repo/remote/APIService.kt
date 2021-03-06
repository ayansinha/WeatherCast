package org.weathercast.data.repo.remote

import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.data.model.WeeksForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [APIService]
 */
interface APIService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Response<WeatherCurrentModel>

    @GET("forecast")
    suspend fun getWeeksData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Response<WeeksForecastModel>
}
