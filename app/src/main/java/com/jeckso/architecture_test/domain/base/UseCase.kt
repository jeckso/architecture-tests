package com.jeckso.architecture_test.domain.base

interface UseCase<Input, Output> {

    suspend fun execute(vararg input: Input?): Output

}