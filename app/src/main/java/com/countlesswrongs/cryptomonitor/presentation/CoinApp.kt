package com.countlesswrongs.cryptomonitor.presentation

import android.app.Application
import androidx.work.Configuration
import com.countlesswrongs.cryptomonitor.data.database.AppDatabase
import com.countlesswrongs.cryptomonitor.data.mapper.CoinMapper
import com.countlesswrongs.cryptomonitor.data.network.api.ApiFactory
import com.countlesswrongs.cryptomonitor.data.workers.RefreshDataWorkerFactory
import com.countlesswrongs.cryptomonitor.di.component.DaggerApplicationComponent

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }

}
