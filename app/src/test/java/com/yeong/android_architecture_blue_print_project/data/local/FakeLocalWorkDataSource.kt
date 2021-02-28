package com.yeong.android_architecture_blue_print_project.data.local

import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

class FakeLocalWorkDataSource : WorkDataSource {

    private val taskList = mutableListOf<Work>()
    private val fakeDispatcher = Dispatchers.Unconfined

    override suspend fun getAllWork(): Result<List<Work>> = withContext(fakeDispatcher) {
        if (taskList.isNotEmpty()) {
            Result.Success(taskList)
        } else {
            Result.Fail(
                LocalWorkDataSourceError.DATA_EMPTY,
                Exception(LocalWorkDataSourceError.DATA_EMPTY_STRING)
            )
        }
    }

    override suspend fun getYetCompleteWorkList(): Result<List<Work>> =
        withContext(fakeDispatcher) {
            val filterTaskList = taskList.filter { !it.isComplete }
            if (filterTaskList.isNotEmpty()) {
                Result.Success(filterTaskList)
            } else {
                Result.Fail(
                    LocalWorkDataSourceError.DATA_EMPTY,
                    Exception(LocalWorkDataSourceError.DATA_EMPTY_STRING)
                )
            }
        }

    override suspend fun getCompleteWorkList(): Result<List<Work>> = withContext(fakeDispatcher) {
        val filterTaskList = taskList.filter { it.isComplete }
        if (filterTaskList.isNotEmpty()) {
            Result.Success(filterTaskList)
        } else {
            Result.Fail(
                LocalWorkDataSourceError.DATA_EMPTY,
                Exception(LocalWorkDataSourceError.DATA_EMPTY_STRING)
            )
        }
    }

    override suspend fun addWork(vararg work: Work) = withContext(fakeDispatcher) {
        taskList.addAll(work)
        taskList.sortBy { it.workCreateDate }
    }

    override suspend fun removeWork(vararg work: Work) {
        taskList.removeAll(work)
    }

    override suspend fun removeAllWork() {
        taskList.clear()
    }

    override suspend fun removeCompleteWork() {
        val removeTargetList = taskList.filter { it.isComplete }
        taskList.removeAll(removeTargetList.toTypedArray())
    }

    override suspend fun updateWork(vararg work: Work) {
        work.forEach { updateWorkItem ->
            val findItem =
                taskList.find { workListItem -> updateWorkItem.workIndex == workListItem.workIndex }
            findItem?.apply {
                title = updateWorkItem.title
                content = updateWorkItem.content
            }
        }
    }
}