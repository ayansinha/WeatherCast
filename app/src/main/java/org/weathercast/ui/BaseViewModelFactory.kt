package org.weathercast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.weathercast.data.repo.remote.RemoteRepository
import org.weathercast.ui.viewmodel.ForecastViewModel

class BaseViewModelFactory<T>(val remote: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = remote() as T
}

/*class BaseViewModelFactory(private val remote: RemoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(remote) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}*/
