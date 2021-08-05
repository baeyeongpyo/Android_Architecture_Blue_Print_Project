package com.yeong.reuslt

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

class FlowResult<T>(private val result: Result<T>) {

    suspend fun collect(action: suspend Result<T>.() -> Unit) =
        flowOf(Result.Loading, result, Result.Done).collect(action)
}