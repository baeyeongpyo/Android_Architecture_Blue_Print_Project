package com.yeong.android_architecture_blue_print_project.data.local

import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalWorkDataSourceImpl(
    private val dao: WorkDAO,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WorkDataSource {
    override suspend fun getAllWork(): Result<List<Work>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertWork(vararg work: Work) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWork(vararg work: Work) {
        TODO("Not yet implemented")
    }
}