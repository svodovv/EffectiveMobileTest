package com.example.effectivemobiletest.utils.network.adapter

import com.google.gson.annotations.SerializedName


internal class NewsErrorNetwork(
    @SerializedName("status")
    val status: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)