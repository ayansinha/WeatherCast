package org.weathercast.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import org.weathercast.ui.factory.BaseViewModelFactory

@Module
abstract class ViewModelFactoryModule {

    //@ActivityScope
    @Binds
    abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}
