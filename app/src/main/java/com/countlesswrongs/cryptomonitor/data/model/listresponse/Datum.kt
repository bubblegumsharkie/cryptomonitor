package com.countlesswrongs.cryptomonitor.data.model.listresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(

    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null

)
