package com.jeckso.reddit.data.network.rest.models

import com.google.gson.annotations.SerializedName

data class TopListResponse (
    @SerializedName("kind")
    val kind: String, // 3023
    @SerializedName("data")
    val data: Data, // 3023
)