package com.countlesswrongs.cryptomonitor.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_details, CoinDetailFragment.newInstance(fSym))
                .commit()
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
