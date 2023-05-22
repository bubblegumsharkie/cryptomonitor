package com.countlesswrongs.cryptomonitor.presentation.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.countlesswrongs.cryptomonitor.domain.entity.CoinInfoEntity

object CoinInfoDiffCallback: ItemCallback<CoinInfoEntity>() {
    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }

}
