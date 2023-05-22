package com.countlesswrongs.cryptomonitor.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.countlesswrongs.cryptomonitor.R
import com.countlesswrongs.cryptomonitor.databinding.ItemCoinInfoBinding
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfoEntity, CoinInfoAdapter.CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)

        with(holder.binding) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            textViewSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            textViewPrice.text = coin.price
            textViewLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
            Picasso.get().load(coin.imageUrl).into(imageViewLogo)

            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }
    }

    inner class CoinInfoViewHolder(val binding: ItemCoinInfoBinding) : ViewHolder(binding.root)

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoEntity: CoinInfoEntity)
    }

}
