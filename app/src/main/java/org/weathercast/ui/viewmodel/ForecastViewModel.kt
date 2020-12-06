package org.weathercast.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.data.model.WeeksForecastModel
import org.weathercast.data.repo.remote.RemoteRepository
import org.weathercast.util.Constants.Companion.ERROR_MSG
import org.weathercast.util.Resource
import org.weathercast.util.Status
import retrofit2.Response

class ForecastViewModel(private val remote: RemoteRepository): ViewModel() {

    private lateinit var job: Job
    private val _currentWeatherModel = MutableLiveData<Resource<Response<WeatherCurrentModel>>>()
    val resultCurrentWeather: LiveData<Resource<Response<WeatherCurrentModel>>> get() = _currentWeatherModel

    private val _weeksData = MutableLiveData<Resource<Response<WeeksForecastModel>>>()
    val resultWeeksData: LiveData<Resource<Response<WeeksForecastModel>>> get() = _weeksData

    init {
        Log.d("TAG", "INIT_BLOCK: --> init block")
        job = Job()
        fetchCurrentWeather()
    }

    /**
     * fetch current weather data
     */
    private fun fetchCurrentWeather() {
        Log.d("TAG", "INIT_BLOCK: --> fetchCurrentWeather")
        viewModelScope.launch(IO + job) {
            _currentWeatherModel.postValue(Resource.loading(null))
            try {
                _currentWeatherModel.postValue(Resource.success(remote.getCurrentWeather("pune")))
                fetchWeeksData()
            }catch (e: Exception) {
                _currentWeatherModel.postValue(Resource.error(ERROR_MSG, null))
                e.message?: ERROR_MSG
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
                _weeksData.postValue(Resource.success(remote.getWeekWeather("pune")))
            }catch (e: Exception) {
                _weeksData.postValue(Resource.error(ERROR_MSG, null))
                e.message?: ERROR_MSG
            }
        }
    }

    /*fun getCountryModel(): LiveData<Resource<Response<WeatherCurrentModel>>> {
        return _currentWeatherModel
    }

    fun getWeeksModel(): LiveData<Resource<Response<WeeksForecastModel>>> {
        return _weeksData
    }*/

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
