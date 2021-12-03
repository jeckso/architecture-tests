package com.jeckso.reddit.data.network.rest.services

import com.jeckso.reddit.data.network.rest.models.TopListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PostsService {

    companion object {
        private const val ENDPOINT_GET_TOP_POSTS = "top.json"
    }

    @GET(ENDPOINT_GET_TOP_POSTS)
    suspend fun getTopPosts(@Query("limit") limit: Int, @Query("count") count: Int): TopListResponse
}