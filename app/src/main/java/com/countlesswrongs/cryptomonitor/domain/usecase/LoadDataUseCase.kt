package com.countlesswrongs.cryptomonitor.domain.usecase

import com.countlesswrongs.cryptomonitor.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() = repository.loadData()

}
