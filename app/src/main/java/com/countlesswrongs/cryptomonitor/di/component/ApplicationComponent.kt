package com.countlesswrongs.cryptomonitor.di.component

import android.app.Application
import com.countlesswrongs.cryptomonitor.di.module.DataModule
import com.countlesswrongs.cryptomonitor.di.module.ViewModelModule
import com.countlesswrongs.cryptomonitor.presentation.CoinDetailFragment
import com.countlesswrongs.cryptomonitor.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
