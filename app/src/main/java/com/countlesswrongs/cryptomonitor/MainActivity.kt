package com.countlesswrongs.cryptomonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.viewmodel.CoinViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.getDetailedInfo("BTC").observe(this) {
            Log.d("TEST_DATA_LOADING", it.toString())
        }
//        viewModel.priceList.observe(this) {
//            Log.d("TEST_DATA", "Succ in Activity: $it")
//        }
    }


}