package com.yeong.reuslt

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FlowResult private constructor() {

    companion object {
        suspend fun <T> collect(
            result: suspend () -> Result<T>,
            action: suspend Result<T>.() -> Unit
        ) =
            flow {
                emit(Result.Loading)
                emit(result())
                emit(Result.Done)
            }.collect { it.action() }

    }
}