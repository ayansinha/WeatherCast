package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") cityName: String, @Query(BuildConfig.API_KEY) apiKey: String)

    @GET("forecast")
    suspend fun getWeeksData(@Query("q") cityName: String, @Query(BuildConfig.API_KEY) apiKey: String)
}