package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class GalleryData(
    @SerializedName("items")
    val items: List<Item>
)