package com.countlesswrongs.cryptomonitor.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.countlesswrongs.cryptomonitor.api.ApiFactory
import com.countlesswrongs.cryptomonitor.database.AppDatabase
import com.countlesswrongs.cryptomonitor.model.detailedresponse.CoinPriceInfo
import com.countlesswrongs.cryptomonitor.model.detailedresponse.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .map { coinInfoListOfData ->
                coinInfoListOfData.data?.map {
                    it.coinInfo?.name
                }?.joinToString(",").toString()
            }
            .flatMap {
                ApiFactory.apiService.getFullPriceList(fSyms = it)
            }
            .map { getPriceListFromRawData(it) }
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("TEST_DATA", "Success ${it.toString()}")
            }, {
                Log.d("TEST_DATA", "Failure ${it.message.toString()}")
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
