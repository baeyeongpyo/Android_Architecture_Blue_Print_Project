package com.yeong.android_architecture_blue_print_project.data.local

import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onFail
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalWorkDataSourceTest {

    private val dataSource = FakeLocalWorkDataSource()

    @Before
    fun getAllTest() = runBlockingTest {
        val beforeListTest = dataSource.getAllWork()
        val result = beforeListTest as Result.Fail
        assertEquals(LocalWorkDataSourceError.DATA_EMPTY, result.code)
    }

    @Test
    fun workListEmptyTest() = runBlockingTest {
        dataSource.removeAllWork()
        val result = dataSource.getAllWork()
        result.onSuccess {
            assert(false)
        }.onFail { code, _ ->
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, code)
        }
    }

    @Test
    fun workAddTest() = runBlockingTest {

        dataSource.removeAllWork()

        val beforeWorkList = dataSource.getAllWork()
        val newWorkData = Work("test Title", "test Content")
        dataSource.addWork(newWorkData)

        val afterWorkList = dataSource.getAllWork()

        if (beforeWorkList is Result.Fail && afterWorkList is Result.Success) {
            assertEquals(1, afterWorkList.data.size)
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, beforeWorkList.code)
        } else {
            assert(false)
        }
    }

    @Test
    fun workCompleteSelectQueryTest() = runBlockingTest {

        dataSource.removeAllWork()

        val beforeWorkList = dataSource.getAllWork()
        beforeWorkList.onSuccess {
            assert(false)
        }.onFail { code, _ ->
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, code)
        }

        val beforeYetCompleteWorkList = dataSource.getAllWork()
        beforeYetCompleteWorkList.onSuccess {
            assert(false)
        }.onFail { code, _ ->
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, code)
        }

        val beforeCompleteWorkList = dataSource.getAllWork()
        beforeCompleteWorkList.onSuccess {
            assert(false)
        }.onFail { code, _ ->
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, code)
        }

    }

    @Test
    fun workCompleteStatusUpdate() = runBlockingTest {

        dataSource.removeAllWork()

        val newWorkData = Work("test title", "test content")

        dataSource.addWork(newWorkData)

        val addAfterWorkList = dataSource.getAllWork()
        val addAfterYetCompleteWorkList = dataSource.getYetCompleteWorkList()
        val addAfterCompleteWorkList = dataSource.getCompleteWorkList()

        if (
            addAfterWorkList is Result.Success
            && addAfterYetCompleteWorkList is Result.Success
            && addAfterCompleteWorkList is Result.Fail
        ) {
            assertEquals(1, addAfterWorkList.data.size)
            assertEquals(1, addAfterYetCompleteWorkList.data.size)
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, addAfterYetCompleteWorkList.data.size)
        } else {
            assert(false)
        }

        val updateWorkData = (addAfterYetCompleteWorkList as Result.Success).data[0]
        updateWorkData.isComplete = true
        dataSource.updateWork(updateWorkData)

        val afterUpdateWorkList = dataSource.getAllWork()
        val afterUpdateYetCompleteWorkList = dataSource.getYetCompleteWorkList()
        val afterUpdateCompleteWorkList = dataSource.getCompleteWorkList()

        if (afterUpdateWorkList is Result.Success
            && afterUpdateYetCompleteWorkList is Result.Fail
            && afterUpdateCompleteWorkList is Result.Success
        ) {
            assertEquals(1, afterUpdateWorkList.data.size)
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, afterUpdateYetCompleteWorkList.code)
            assertEquals(1, afterUpdateCompleteWorkList.data.size)
        } else {
            assert(false)
        }

    }

    @Test
    fun workSelectWorkRemove() = runBlockingTest {

        dataSource.removeAllWork()

        val newWorkData = Work("test Title", "test Content")

        dataSource.addWork(newWorkData)

        val beforeRemoveWorkList = dataSource.getAllWork()
        val removeTargetData = (beforeRemoveWorkList as Result.Success).data[0]
        dataSource.removeWork(removeTargetData)

        val afterRemoveWorkList = dataSource.getAllWork()

        @Suppress("USELESS_IS_CHECK")
        if (afterRemoveWorkList is Result.Fail) {
            assertEquals(LocalWorkDataSourceError.DATA_EMPTY, afterRemoveWorkList.code)
        } else {
            assert(false)
        }

    }


}