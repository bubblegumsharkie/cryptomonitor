package com.countlesswrongs.cryptomonitor.di.module

import androidx.lifecycle.ViewModel
import com.countlesswrongs.cryptomonitor.di.key.ViewModelKey
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}
