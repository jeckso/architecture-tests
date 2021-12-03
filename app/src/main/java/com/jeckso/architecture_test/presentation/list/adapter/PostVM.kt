package com.jeckso.architecture_test.presentation.list.adapter

data class PostVM (
    val id: String,
    val authorFullName:String,
    val createdUtc: Double,
    val created: Double,
    val score: Int,
    val thumbnail: String,
    val title: String,
    val url: String,
)