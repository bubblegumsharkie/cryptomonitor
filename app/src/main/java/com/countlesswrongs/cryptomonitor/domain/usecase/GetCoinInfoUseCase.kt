package com.countlesswrongs.cryptomonitor.domain.usecase

import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}
