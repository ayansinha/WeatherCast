package org.weathercast.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * [BaseViewModelFactory]
 */
/*@Suppress("UNCHECKED_CAST")
@Singleton
//@ApplicationScope
class BaseViewModelFactory<T>@Inject constructor(private val remote: () -> T ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = remote() as T
}*/

@Suppress("UNCHECKED_CAST")
@Singleton
class BaseViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]
        if (creator == null) {
            creator = requireNotNull(
                creators
                    .entries
                    .firstOrNull { modelClass.isAssignableFrom(it.key) }
                    ?.value
            ) {
                "unknown model class $modelClass"
            }
        }

        return creator.get() as T
    }
}
