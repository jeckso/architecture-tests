package com.jeckso.architecture_test.android.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T, *>>(
    _listener: OnItemClickListener<T>?
) : RecyclerView.Adapter<VH>() {

    var listener: OnItemClickListener<T>? = _listener
        private set

    private val _items: MutableList<T> = mutableListOf()

    var items: List<T>
        set(value) {
            _items.clear()
            _items.addAll(value)
            notifyDataSetChanged()
        }
        get() = _items

    fun add(position: Int = _items.size, items: List<T>) {
        _items.addAll(position, items)
        notifyItemRangeInserted(0, items.lastIndex)
    }

    fun remove(position: Int) {
        _items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(item: T) {
        val position = _items.indexOf(item)
        if (position != RecyclerView.NO_POSITION) {
            remove(position)
        }
    }

    operator fun set(position: Int, item: T) {
        _items[position] = item
        notifyItemChanged(position)
    }

    operator fun get(position: Int): T? {
        return _items.getOrNull(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(_items[position], listener)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int {
        return _items.size
    }
}