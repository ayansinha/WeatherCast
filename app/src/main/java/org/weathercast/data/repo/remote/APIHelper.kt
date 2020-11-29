package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig

class APIHelper(private val apiService: APIService) {
    suspend fun getCurrentWeather(cityName: String) = apiService.getCurrentWeather(cityName , BuildConfig.API_KEY)
}