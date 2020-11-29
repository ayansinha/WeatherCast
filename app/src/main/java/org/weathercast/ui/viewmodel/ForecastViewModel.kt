package org.weathercast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import org.weathercast.data.repo.remote.RemoteRepository

class ForecastViewModel(private val remote: RemoteRepository): ViewModel() {

    private lateinit var job: Job
    private val currentWeatherModel = MutableLiveData<ForecastViewModel>()
    private val _resultCurrentWeatherModel: LiveData<ForecastViewModel> = currentWeatherModel


    init {

    }
}