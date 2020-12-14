package org.weathercast.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.weathercast.di.component.DaggerWeatherAppComponent
import timber.log.Timber

class WeatherCastApplication : DaggerApplication() {

    private val applicationInjector = DaggerWeatherAppComponent.builder()
        .bindApplication(this)
        .bindContext(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
