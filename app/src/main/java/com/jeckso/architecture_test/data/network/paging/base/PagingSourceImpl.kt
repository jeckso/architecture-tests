package com.jeckso.architecture_test.data.network.paging.base

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingSourceImpl<T: Any> constructor(
    private val retriever: suspend (Int, Int) -> LoadResult<Int, T>
) : PagingSource<Int, T>() {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            retriever(page, params.loadSize)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int = DEFAULT_PAGE_INDEX
}