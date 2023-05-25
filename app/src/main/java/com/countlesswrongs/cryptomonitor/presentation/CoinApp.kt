package com.countlesswrongs.cryptomonitor.presentation

import android.app.Application
import androidx.work.Configuration
import com.countlesswrongs.cryptomonitor.data.workers.WorkerFactory
import com.countlesswrongs.cryptomonitor.di.component.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

}
