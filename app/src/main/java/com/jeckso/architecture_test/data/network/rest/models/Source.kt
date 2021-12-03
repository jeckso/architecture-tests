package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("height")
    val height: Int, // 3023
    @SerializedName("url")
    val url: String, // https://preview.redd.it/vv2yl3jvde281.jpg?auto=webp&amp;s=e96abedb036ef092edf05bce65949d50f83bc171
    @SerializedName("width")
    val width: Int // 3023
)