package com.countlesswrongs.cryptomonitor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var imageViewLogo: ImageView
    private lateinit var textViewFromSymbol: TextView
    private lateinit var textViewToSymbol: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewLowestPriceToday: TextView
    private lateinit var textViewHighestPriceToday: TextView
    private lateinit var textViewLastMarket: TextView
    private lateinit var textViewLastUpdate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        initViews()



        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            Toast.makeText(
                this,
                "There is no detailed info about $EXTRA_FROM_SYMBOL",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val fSym = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fSym != null) {
            viewModel.getDetailedInfo(fSym).observe(this) {
                textViewFromSymbol.text = it.fromSymbol
                textViewToSymbol.text = it.toSymbol
                textViewPrice.text = it.price.toString()
                textViewLowestPriceToday.text = it.lowDay.toString()
                textViewHighestPriceToday.text = it.highDay.toString()
                textViewLastMarket.text = it.lastMarket
                textViewLastUpdate.text = it.getFormattedTime()
                Picasso.get().load(it.getFullImageUrl()).into(imageViewLogo)
            }
        }
    }

    private fun initViews() {
        textViewFromSymbol = findViewById(R.id.textViewFromSymbol)
        textViewToSymbol = findViewById(R.id.textViewToSymbol)
        imageViewLogo = findViewById(R.id.imageViewLogo)
        textViewPrice = findViewById(R.id.textViewPrice)
        textViewLowestPriceToday = findViewById(R.id.textViewLowestPriceToday)
        textViewHighestPriceToday = findViewById(R.id.textViewHighestPriceToday)
        textViewLastMarket = findViewById(R.id.textViewLastMarket)
        textViewLastUpdate = findViewById(R.id.textViewLastUpdate)
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

}
