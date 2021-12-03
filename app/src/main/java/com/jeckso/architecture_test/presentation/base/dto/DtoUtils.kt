package com.jeckso.architecture_test.presentation.base.dto

import androidx.recyclerview.widget.DiffUtil
import com.jeckso.architecture_test.presentation.list.adapter.PostVM

object PostDiffCallback: DiffUtil.ItemCallback<PostVM>() {

    override fun areItemsTheSame(oldItem: PostVM, newItem: PostVM): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostVM, newItem: PostVM): Boolean {
        return oldItem == newItem
    }
}