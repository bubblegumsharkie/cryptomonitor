package com.countlesswrongs.cryptomonitor.data.network.model.detailedresponse

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(

    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null

)
