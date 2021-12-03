package com.jeckso.architecture_test.data.network.exception

class InternalHttpException(
    val code: Int,
    override val message: String?
) : Exception()