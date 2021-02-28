package com.yeong.android_architecture_blue_print_project.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yeong.android_architecture_blue_print_project.data.Work
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkDatabaseDAOAndroidTest : TestCase() {

    private lateinit var db: WorkDatabase
    private lateinit var workDao: WorkDAO

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WorkDatabase::class.java).build()
        workDao = db.workDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun workDataBase_add_work_test() = runBlocking {
        val newWorkData = Work(null, "test title", "test content data")
        workDataAddTest(newWorkData)
        return@runBlocking
    }

    @Test
    fun workDataBase_add_work_remove_test() = runBlocking {
        val newWorkData = Work("test title", "test content data")
        val removeBeforeList = workDataAddTest(newWorkData)

        workDao.removeWork(removeBeforeList[0])
        val removeAfterListData = workDao.allWork()

        assertEquals(removeBeforeList.size - 1, removeAfterListData.size)
    }

    @Test
    fun workDataBase_add_work_update_test() = runBlocking {
        val newWorkData = Work("test title", "test content data")
        val updateBeforeWorkList = workDataAddTest(newWorkData)

        val updateTargetWorkData = updateBeforeWorkList[0]
        updateTargetWorkData.title = "test title change"
        updateTargetWorkData.content = "test content change"

        workDao.updateWork(updateTargetWorkData)

        val updateAfterWorkList = workDao.allWork()
        val updateCheckTargetData = updateAfterWorkList[0]

        assertEquals(updateTargetWorkData.title, updateCheckTargetData.title)
        assertEquals(updateTargetWorkData.content, updateCheckTargetData.content)
    }

    private fun workDataAddTest(workData: Work) = runBlocking {
        val beforeWorkList = workDao.allWork()

        workDao.addWork(workData)

        val afterWorkList = workDao.allWork()

        assertEquals(beforeWorkList.size + 1, afterWorkList.size)

        return@runBlocking afterWorkList
    }

}