package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Int, // 89674952
    @SerializedName("media_id")
    val mediaId: String // e8qp5fjtne281
)