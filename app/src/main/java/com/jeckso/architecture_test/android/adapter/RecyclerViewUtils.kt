package com.jeckso.architecture_test.android.adapter

fun interface OnItemClickListener<T> {

    fun onItemClick(item: T, position: Int)

}