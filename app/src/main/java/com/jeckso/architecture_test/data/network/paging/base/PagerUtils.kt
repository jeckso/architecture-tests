package com.jeckso.architecture_test.data.network.paging.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource

const val DEFAULT_PAGE_SIZE = 10

private val PAGING_CONFIG = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)


fun <T : Any> pagerSourceOfUnknownTotal(
    block: suspend (Int) -> List<T>
): PagingSourceImpl<T> {
    return PagingSourceImpl { page, loadSize ->
        val result = block(page)
        val nextKey = if (result.size == loadSize) {
            page + 1
        } else {
            null
        }
        PagingSource.LoadResult.Page(result, null, nextKey)
    }
}

fun <T : Any> pagerOf(
    config: PagingConfig = PAGING_CONFIG,
    block: suspend (Int) -> List<T>
): Pager<Int, T> {
    return Pager(config, 1, { pagerSourceOfUnknownTotal(block) })
}

fun <T : Any> pagerOf(
    config: PagingConfig = PAGING_CONFIG,
    pagingSource: PagingSource<Int, T>
): Pager<Int, T> {
    return Pager(config, 1, { pagingSource })
}