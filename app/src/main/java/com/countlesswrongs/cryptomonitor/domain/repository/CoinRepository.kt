package com.countlesswrongs.cryptomonitor.domain.repository

import androidx.lifecycle.LiveData
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity>

    suspend fun loadData()
}
