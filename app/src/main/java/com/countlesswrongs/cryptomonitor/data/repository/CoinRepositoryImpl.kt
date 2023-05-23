package com.countlesswrongs.cryptomonitor.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.countlesswrongs.cryptomonitor.data.database.AppDatabase
import com.countlesswrongs.cryptomonitor.data.mapper.CoinMapper
import com.countlesswrongs.cryptomonitor.data.workers.RefreshDataWorker
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity
import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return coinInfoDao.getPriceList().map { modelList ->
            modelList.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

}
