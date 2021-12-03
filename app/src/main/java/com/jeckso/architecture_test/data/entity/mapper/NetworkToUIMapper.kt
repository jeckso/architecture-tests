package com.jeckso.architecture_test.data.entity.mapper

import com.jeckso.architecture_test.data.network.rest.models.Children
import com.jeckso.architecture_test.presentation.list.adapter.PostVM

object NetworkToUIMapper {

    fun map(response: Children): PostVM = with(response.data) {
        PostVM(
            id,
            author,
            createdUtc,
            created,
            score,
            thumbnail,
            title,
            url
        )
    }
}