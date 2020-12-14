package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig

/**
 * [RemoteRepository]
 */
class RemoteRepository(private val apiService: APIService) {
    suspend fun getCurrentWeather(cityName: String) = apiService.getCurrentWeather(cityName, BuildConfig.API_KEY)

    suspend fun getWeekWeather(cityName: String) = apiService.getWeeksData(cityName, BuildConfig.API_KEY)
}
