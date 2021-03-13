package com.francisco.sid.di.module

import com.francisco.sid.ui.activity.MainActivity
import com.francisco.sid.ui.fragment.CheckinFormFragment
import com.francisco.sid.ui.fragment.EventDetailsFragment
import com.francisco.sid.ui.fragment.EventsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindEventsListFragment(): EventsListFragment

    @ContributesAndroidInjector
    abstract fun bindEventDetailsFragment(): EventDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindCheckinFormFragment(): CheckinFormFragment
}