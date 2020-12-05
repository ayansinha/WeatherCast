package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig

class RemoteRepository(private val apiService: APIService) {
    suspend fun getCurrentWeather(cityName: String) = apiService.getCurrentWeather(cityName , BuildConfig.API_KEY)
}