package com.jeckso.architecture_test.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jeckso.architecture_test.data.entity.mapper.NetworkToUIMapper
import com.jeckso.architecture_test.data.network.paging.base.pagerOf
import com.jeckso.architecture_test.presentation.list.adapter.PostVM
import com.jeckso.architecture_test.presentation.util.Failure
import com.jeckso.reddit.data.network.rest.services.PostsService
import com.jeckso.reddit.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val postsService: PostsService
) : BaseViewModel() {

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }

    private val postPager = pagerOf {
        postsService.getTopPosts(DEFAULT_PAGE_SIZE, it * DEFAULT_PAGE_SIZE).data.children
    }

    val items: Flow<PagingData<PostVM>> = postPager.flow
        .map { it.map { NetworkToUIMapper.map(it) } }
        .catch { _errorState.emit(Failure(error = it)) }
        .cachedIn(viewModelScope)
}