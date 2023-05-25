package com.countlesswrongs.cryptomonitor.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.countlesswrongs.cryptomonitor.data.database.dao.CoinInfoDao
import com.countlesswrongs.cryptomonitor.data.mapper.CoinMapper
import com.countlesswrongs.cryptomonitor.data.network.api.ApiService

class RefreshDataWorkerFactory(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            apiService,
            mapper
        )
    }
}