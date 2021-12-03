package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String, // u3Y1j7Wsl2xuOgTcutKoT0LKxJ6FDQfTLam5GnBpzFw
    @SerializedName("resolutions")
    val resolutions: List<Resolution>,
    @SerializedName("source")
    val source: Source,
)