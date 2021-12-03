package com.jeckso.architecture_test.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jeckso.architecture_test.android.adapter.BasePaginationAdapter
import com.jeckso.architecture_test.android.adapter.OnItemClickListener
import com.jeckso.architecture_test.databinding.LiPostBinding
import com.jeckso.architecture_test.presentation.base.dto.PostDiffCallback

class PostAdapter(
    listener: OnItemClickListener<PostVM>
) : BasePaginationAdapter<PostVM, PostVH>(PostDiffCallback, listener) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = LiPostBinding.inflate(inflater, parent, false)
        return PostVH(viewBinding)
    }

}