package com.countlesswrongs.cryptomonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.countlesswrongs.cryptomonitor.adapter.CoinInfoAdapter
import com.countlesswrongs.cryptomonitor.viewmodel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinPriceListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        coinPriceListRecyclerView = findViewById(R.id.recyclerViewCoinPriceList)
        val adapter = CoinInfoAdapter()
        coinPriceListRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
