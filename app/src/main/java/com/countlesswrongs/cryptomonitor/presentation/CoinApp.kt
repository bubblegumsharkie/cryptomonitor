package com.countlesswrongs.cryptomonitor.presentation

import android.app.Application
import com.countlesswrongs.cryptomonitor.di.component.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}
