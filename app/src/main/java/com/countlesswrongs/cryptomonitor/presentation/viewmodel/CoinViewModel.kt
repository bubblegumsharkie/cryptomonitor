package com.countlesswrongs.cryptomonitor.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.countlesswrongs.cryptomonitor.data.repository.CoinRepositoryImpl
import com.countlesswrongs.cryptomonitor.domain.usecase.GetCoinInfoListUseCase
import com.countlesswrongs.cryptomonitor.domain.usecase.GetCoinInfoUseCase
import com.countlesswrongs.cryptomonitor.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailedInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}
