package com.jeckso.architecture_test.android.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePaginationAdapter<T: Any, VH : BaseViewHolder<T, *>>(
    diffCallback: DiffUtil.ItemCallback<T>,
    _listener: OnItemClickListener<T>?
) : PagingDataAdapter<T, VH>(diffCallback) {

    var listener: OnItemClickListener<T>? = _listener
        private set

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.unbind()
    }
}