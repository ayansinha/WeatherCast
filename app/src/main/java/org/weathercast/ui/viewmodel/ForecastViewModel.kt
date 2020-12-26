package org.weathercast.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.data.model.WeeksForecastModel
import org.weathercast.data.repo.remote.RemoteRepository
import org.weathercast.util.Constants.Companion.ERROR_MSG
import org.weathercast.util.Resource
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * [ForecastViewModel]
 */
class ForecastViewModel @Inject constructor(
    private val remote: RemoteRepository /*, private val cityName: String*/
): ViewModel() {

    private lateinit var job: Job
        private val _currentWeatherModel =
            MutableLiveData<Resource<Response<WeatherCurrentModel>>>()
        val resultCurrentWeather: LiveData<Resource<Response<WeatherCurrentModel>>>
            get() = _currentWeatherModel

        private val _weeksData =
            MutableLiveData<Resource<Response<WeeksForecastModel>>>()
        val resultWeeksData: LiveData<Resource<Response<WeeksForecastModel>>>
            get() = _weeksData

    init {
        Timber.d("INIT_BLOCK: --> init block")
        job = Job()
        fetchCurrentWeather()
    }

    /**
     * fetch current weather data
     */
    private fun fetchCurrentWeather() {
        Timber.d("INIT_BLOCK: --> fetchCurrentWeather")
        viewModelScope.launch(IO + job) {
            _currentWeatherModel.postValue(Resource.loading(null))
            try {
                _currentWeatherModel.postValue(Resource.success(remote.getCurrentWeather("Pune")))
                launch {
                    fetchWeeksData()
                }
            } catch (e: IOException) {
                _currentWeatherModel.postValue(Resource.error(ERROR_MSG, null))
            }
        }
    }

    /**
     * fetch weeks data
     */
    private fun fetchWeeksData() {
        viewModelScope.launch(IO + job) {
            _weeksData.postValue(Resource.loading(null))
            try {
                _weeksData.postValue(Resource.success(remote.getWeekWeather("Pune")))
            } catch (e: IOException) {
                _weeksData.postValue(Resource.error(ERROR_MSG, null))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
