package org.weathercast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * [BaseViewModelFactory]
 */
class BaseViewModelFactory<T>(val remote: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = remote() as T
}
