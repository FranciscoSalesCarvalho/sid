package com.francisco.sid.base

import com.francisco.sid.R
import com.francisco.sid.SidApplication
import com.francisco.sid.util.testing.di.component.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TestSidApplication: SidApplication() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_Sid)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerTestAppComponent.builder()
            .build()

        component.inject(this)

        return component
    }
}