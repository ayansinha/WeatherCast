package org.weathercast.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.weathercast.ui.view.ActivityForecast

@Module
abstract class ActivityModule {

    //@ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeForecastActivity(): ActivityForecast
}
