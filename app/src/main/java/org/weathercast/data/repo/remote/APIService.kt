package org.weathercast.data.repo.remote

import androidx.lifecycle.LiveData
import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Response<WeatherCurrentModel>

    @GET("forecast")
    suspend fun getWeeksData(@Query("q") cityName: String, @Query("appid") apiKey: String): List<LiveData<WeatherCurrentModel>>
}