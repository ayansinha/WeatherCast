package org.weathercast.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import org.weathercast.app.WeatherCastApplication
import org.weathercast.di.module.ActivityModule
import org.weathercast.di.module.NetworkModule
import org.weathercast.di.module.ViewModelFactoryModule
import org.weathercast.di.module.ViewModelModule
import javax.inject.Singleton

//@ApplicationScope
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        NetworkModule::class
    ]
)
interface WeatherAppComponent: AndroidInjector<WeatherCastApplication> {
    @Component.Builder
    interface Builder {
        /**
         * Binds the [Application] into the dependency graph
         */
        @BindsInstance
        fun bindApplication(application: Application): Builder

        /**
         * Binds the [Context]
         */
        @BindsInstance
        fun bindContext(context: Context): Builder

        /**
         * provides the build method for [Component.Builder]
         */
        fun build(): WeatherAppComponent
    }

    //fun inject( forecastViewModel: ForecastViewModel )

    override fun inject(weatherApp: WeatherCastApplication)
}
