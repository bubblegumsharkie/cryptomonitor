package com.countlesswrongs.cryptomonitor.domain.usecase

import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}
