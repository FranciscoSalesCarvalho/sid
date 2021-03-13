package com.francisco.sid.util.testing.di.component

import com.francisco.sid.di.component.AppComponent
import com.francisco.sid.di.component.module.ContextModule
import com.francisco.sid.di.module.UIBindingModule
import com.francisco.sid.di.module.ViewModelModule
import com.francisco.sid.util.testing.di.module.TestNetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        TestNetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        UIBindingModule::class
    ]
)
interface TestAppComponent : AppComponent {

}