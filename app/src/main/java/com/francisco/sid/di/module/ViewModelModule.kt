package com.francisco.sid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.francisco.sid.di.factory.ViewModelFactory
import com.francisco.sid.di.factory.ViewModelKey
import com.francisco.sid.viewmodel.CheckinViewModel
import com.francisco.sid.viewmodel.EventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    abstract fun bindEventsViewModel(eventsViewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CheckinViewModel::class)
    abstract fun bindCheckinViewModel(checkinViewModel: CheckinViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}