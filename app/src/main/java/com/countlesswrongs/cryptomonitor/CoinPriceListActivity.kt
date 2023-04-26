package com.countlesswrongs.cryptomonitor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.countlesswrongs.cryptomonitor.adapter.CoinInfoAdapter
import com.countlesswrongs.cryptomonitor.model.detailedresponse.CoinPriceInfo
import com.countlesswrongs.cryptomonitor.viewmodel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinPriceListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        coinPriceListRecyclerView = findViewById(R.id.recyclerViewCoinPriceList)

        val adapter = CoinInfoAdapter(applicationContext)
        coinPriceListRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("On_CLICK_TEST", coinPriceInfo.fromSymbol)
            }

        }

        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
