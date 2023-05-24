package com.countlesswrongs.cryptomonitor.di.component

import android.app.Application
import com.countlesswrongs.cryptomonitor.di.module.DataModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
