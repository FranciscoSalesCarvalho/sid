package com.francisco.sid

import com.francisco.sid.di.component.AppComponent
import com.francisco.sid.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class SidApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        component.inject(this)

        return component
    }
}