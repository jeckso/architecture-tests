package com.jeckso.architecture_test.data.network.rest.models


import com.google.gson.annotations.SerializedName
import com.jeckso.reddit.data.network.rest.models.ChildrenData

data class Children(
    @SerializedName("data")
    val data: ChildrenData,
    @SerializedName("kind")
    val kind: String // t3
)