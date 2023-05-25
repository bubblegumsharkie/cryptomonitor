package com.countlesswrongs.cryptomonitor.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.countlesswrongs.cryptomonitor.data.database.dao.CoinInfoDao
import com.countlesswrongs.cryptomonitor.data.mapper.CoinMapper
import com.countlesswrongs.cryptomonitor.data.network.api.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
                Log.d(
                    "LOAD_DATA",
                    "There was a problem with loading data, here's the message: \n $e"
                )
            }
            delay(UPDATE_DELAY)
        }
    }

    companion object {

        private const val UPDATE_DELAY: Long = 10_000
        const val WORKER_NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .build()
        }
    }
}
