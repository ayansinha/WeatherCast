package org.weathercast.data.repo.remote

import org.weathercast.BuildConfig

class RemoteRepository(private val apiHelper: APIHelper , private val cityName: String) {
    suspend fun getCurrentWeather() = apiHelper.getCurrentWeather(cityName)
}