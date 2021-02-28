package com.yeong.android_architecture_blue_print_project.data.local

import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkDataSource
import com.yeong.android_architecture_blue_print_project.data.local.LocalWorkDataSourceError.DATA_EMPTY
import com.yeong.android_architecture_blue_print_project.data.local.LocalWorkDataSourceError.DATA_EMPTY_STRING
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class LocalWorkDataSourceImpl(
    private val dao: WorkDAO,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WorkDataSource {
    override suspend fun getAllWork(): Result<List<Work>> = withContext(dispatcher) {
        try {
            val result = dao.allWork()
            if (!result.isNullOrEmpty()) {
                return@withContext Result.Success(result)
            } else {
                return@withContext Result.Fail(DATA_EMPTY, Exception(DATA_EMPTY_STRING))
            }
        } catch (e: Exception) {
            return@withContext Result.Fail(e)
        }
    }

    override suspend fun getYetCompleteWorkList(): Result<List<Work>> = withContext(dispatcher) {
        try {
            val result = dao.isCompleteWorkList(false)
            if (!result.isNullOrEmpty()) {
                return@withContext Result.Success(result)
            } else {
                return@withContext Result.Fail(DATA_EMPTY, Exception(DATA_EMPTY_STRING))
            }
        } catch (e: Exception) {
            return@withContext Result.Fail(e)
        }
    }

    override suspend fun getCompleteWorkList(): Result<List<Work>> = withContext(dispatcher) {
        try {
            val result = dao.isCompleteWorkList(true)
            if (!result.isNullOrEmpty()) {
                return@withContext Result.Success(result)
            } else {
                return@withContext Result.Fail(DATA_EMPTY, Exception(DATA_EMPTY_STRING))
            }
        } catch (e: Exception) {
            return@withContext Result.Fail(e)
        }
    }

    override suspend fun addWork(vararg work: Work) = withContext(dispatcher) {
        dao.addWork(*work)
    }

    override suspend fun removeWork(vararg work: Work) = withContext(dispatcher) {
        dao.removeWork(*work)
    }
}