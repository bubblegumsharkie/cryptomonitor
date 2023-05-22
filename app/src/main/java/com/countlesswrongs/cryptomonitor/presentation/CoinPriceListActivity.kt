package com.countlesswrongs.cryptomonitor.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.presentation.adapter.CoinInfoAdapter
import com.countlesswrongs.cryptomonitor.data.network.model.detailedresponse.CoinInfoDto
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel

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
            override fun onCoinClick(coinInfoDto: CoinInfoDto) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfoDto.fromSymbol
                )
                startActivity(intent)
            }
        }

        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
