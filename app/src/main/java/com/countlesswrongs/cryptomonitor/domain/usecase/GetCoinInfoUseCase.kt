package com.countlesswrongs.cryptomonitor.domain.usecase

import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}
