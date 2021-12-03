package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName
import com.jeckso.architecture_test.data.network.rest.models.Children

data class Data(
    @SerializedName("after")
    val after: String, // t3_r4f1b3
    @SerializedName("before")
    val before: String?, // null
    @SerializedName("children")
    val children: List<Children>,
    @SerializedName("dist")
    val dist: Int, // 2
    @SerializedName("geo_filter")
    val geoFilter: String,
    @SerializedName("modhash")
    val modhash: String
)