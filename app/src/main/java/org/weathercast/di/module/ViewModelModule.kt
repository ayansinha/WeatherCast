package org.weathercast.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.weathercast.di.viewmodelkey.ViewModelKey
import org.weathercast.ui.viewmodel.ForecastViewModel
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    //@ActivityScope
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel
}
