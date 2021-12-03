package com.jeckso.architecture_test.presentation.field

interface MutableFieldHolder<T : Any> : FieldHolder<T> {

    fun setValue(value: T?)
}