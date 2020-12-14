package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig
import javax.inject.Inject

/**
 * [RemoteRepository]
 */
class RemoteRepository @Inject constructor(private val apiService: APIService) {
    suspend fun getCurrentWeather(cityName: String) = apiService.getCurrentWeather(cityName, BuildConfig.API_KEY)

    suspend fun getWeekWeather(cityName: String) = apiService.getWeeksData(cityName, BuildConfig.API_KEY)
}
