package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("enabled")
    val enabled: Boolean, // true
    @SerializedName("images")
    val images: List<Image>
)