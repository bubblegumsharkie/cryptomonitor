package com.countlesswrongs.cryptomonitor.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.data.model.detailedresponse.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            textViewSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            textViewPrice.text = coin.price.toString()
            textViewLastUpdate.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(imageViewLogoCoin)

            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }

    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageViewLogoCoin: ImageView
        val textViewSymbols: TextView
        val textViewPrice: TextView
        val textViewLastUpdate: TextView

        init {
            imageViewLogoCoin = itemView.findViewById(R.id.imageViewLogo)
            textViewSymbols = itemView.findViewById(R.id.textViewSymbols)
            textViewPrice = itemView.findViewById(R.id.textViewPrice)
            textViewLastUpdate = itemView.findViewById(R.id.textViewLastUpdate)
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }

}
