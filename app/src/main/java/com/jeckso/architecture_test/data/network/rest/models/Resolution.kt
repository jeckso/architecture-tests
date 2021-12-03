package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class Resolution(
    @SerializedName("height")
    val height: Int, // 108
    @SerializedName("url")
    val url: String, // https://preview.redd.it/vv2yl3jvde281.jpg?width=108&amp;crop=smart&amp;auto=webp&amp;s=17b0c73eb4868d988be335bf4ef1065d3b533a6c
    @SerializedName("width")
    val width: Int // 108
)