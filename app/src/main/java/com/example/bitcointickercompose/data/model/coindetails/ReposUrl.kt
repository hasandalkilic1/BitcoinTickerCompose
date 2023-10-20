package com.example.bitcointickercompose.data.model.coindetails

import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("bitbucket")
    val bitbucket: List<Any?>?,
    @SerializedName("github")
    val github: List<String?>?
)