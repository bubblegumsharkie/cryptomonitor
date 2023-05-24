package com.countlesswrongs.cryptomonitor.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.databinding.ActivityCoinPriceListBinding
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity
import com.countlesswrongs.cryptomonitor.presentation.adapter.CoinInfoAdapter
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]

        val adapter = CoinInfoAdapter(applicationContext)
        binding.recyclerViewCoinPriceList.adapter = adapter
        binding.recyclerViewCoinPriceList.itemAnimator = null
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoEntity: CoinInfoEntity) {
                if (isHorizontalOrientation()) {
                    launchDetailActivity(coinInfoEntity.fromSymbol)
                } else {
                    launchDetailFragment(coinInfoEntity.fromSymbol)
                }
            }
        }

        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isHorizontalOrientation() = binding.fragmentContainerDetails == null

    private fun launchDetailActivity(fSym: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fSym
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fSym: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_details, CoinDetailFragment.newInstance(fSym))
            .addToBackStack(null)
            .commit()
    }
}
