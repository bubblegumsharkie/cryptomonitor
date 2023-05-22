package com.countlesswrongs.cryptomonitor.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.databinding.ActivityCoinDetailBinding
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            Toast.makeText(
                this,
                "There is no detailed info about $EXTRA_FROM_SYMBOL",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val fSym = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL_STRING
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailedInfo(fSym).observe(this) {
            with(binding) {
                textViewFromSymbol.text = it.fromSymbol
                textViewToSymbol.text = it.toSymbol
                textViewPrice.text = it.price.toString()
                textViewLowestPriceToday.text = it.lowDay.toString()
                textViewHighestPriceToday.text = it.highDay.toString()
                textViewLastMarket.text = it.lastMarket
                textViewLastUpdate.text = it.lastUpdate
                Picasso.get().load(it.imageUrl).into(imageViewLogo)
            }
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL_STRING = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
