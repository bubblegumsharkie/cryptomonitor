package com.countlesswrongs.cryptomonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.countlesswrongs.cryptomonitor.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val subscribe = ApiFactory.apiService.getFullPriceList(fSyms = "BTC,ETH,EOS")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_DATA", it.toString())
            }, {
                Log.d("TEST_DATA", it.message.toString())
            })
        compositeDisposable.add(subscribe)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}