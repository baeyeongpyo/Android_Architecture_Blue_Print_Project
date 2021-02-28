package com.yeong.android_architecture_blue_print_project.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkRepositoryImpl(
    private val dataSource: WorkDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WorkRepository {

    override suspend fun getAllWork(): Result<List<Work>> = withContext(dispatcher) {
        dataSource.getAllWork()
    }

    override suspend fun getYetCompleteWorkList(): Result<List<Work>> = withContext(dispatcher) {
        dataSource.getYetCompleteWorkList()
    }

    override suspend fun getCompleteWorkList(): Result<List<Work>> = withContext(dispatcher) {
        dataSource.getCompleteWorkList()
    }

    override suspend fun addWork(vararg work: Work) = withContext(dispatcher) {
        dataSource.addWork(*work)
    }

    override suspend fun removeWork(vararg work: Work) = withContext(dispatcher) {
        dataSource.removeWork(*work)
    }
}