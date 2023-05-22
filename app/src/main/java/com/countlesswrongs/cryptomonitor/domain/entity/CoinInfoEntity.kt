package com.countlesswrongs.cryptomonitor.domain.entity

data class CoinInfoEntity(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: String,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String
)