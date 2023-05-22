package com.countlesswrongs.cryptomonitor.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.databinding.ActivityCoinPriceListBinding
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity
import com.countlesswrongs.cryptomonitor.presentation.adapter.CoinInfoAdapter
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        val adapter = CoinInfoAdapter(applicationContext)
        binding.recyclerViewCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoEntity: CoinInfoEntity) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfoEntity.fromSymbol
                )
                startActivity(intent)
            }
        }

        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
