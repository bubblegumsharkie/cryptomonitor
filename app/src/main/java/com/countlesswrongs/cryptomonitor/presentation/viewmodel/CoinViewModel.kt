package com.countlesswrongs.cryptomonitor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.countlesswrongs.cryptomonitor.domain.usecase.GetCoinInfoListUseCase
import com.countlesswrongs.cryptomonitor.domain.usecase.GetCoinInfoUseCase
import com.countlesswrongs.cryptomonitor.domain.usecase.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailedInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }


}
