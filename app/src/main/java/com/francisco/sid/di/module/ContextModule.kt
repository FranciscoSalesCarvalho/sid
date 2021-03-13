package com.francisco.sid.di.component.module

import android.content.Context
import com.francisco.sid.SidApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {

    @Binds
    abstract fun provideContext(application: SidApplication): Context
}