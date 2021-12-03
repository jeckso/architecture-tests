package com.jeckso.architecture_test.presentation.field

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MutableFieldHolderImpl<T : Any>(
    initial: T? = null,
) : MutableFieldHolder<T> {

    private val _valueHolder: MutableStateFlow<T?> = MutableStateFlow(initial)

    override fun setValue(value: T?) {
        _valueHolder.value = value
    }

    val valueHolder: Flow<T?> = _valueHolder

    override val state: Flow<Result<T>> = _valueHolder
        .filterNotNull()
        .flowOn(Dispatchers.Default)
        .map { Result.success(it) }
        .catch { Result.failure<T>(it) }
}