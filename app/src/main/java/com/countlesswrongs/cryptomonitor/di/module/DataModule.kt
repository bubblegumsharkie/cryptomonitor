package com.countlesswrongs.cryptomonitor.di.module

import android.app.Application
import com.countlesswrongs.cryptomonitor.data.database.AppDatabase
import com.countlesswrongs.cryptomonitor.data.database.dao.CoinInfoDao
import com.countlesswrongs.cryptomonitor.data.network.api.ApiFactory
import com.countlesswrongs.cryptomonitor.data.network.api.ApiService
import com.countlesswrongs.cryptomonitor.data.repository.CoinRepositoryImpl
import com.countlesswrongs.cryptomonitor.di.scope.ApplicationScope
import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
