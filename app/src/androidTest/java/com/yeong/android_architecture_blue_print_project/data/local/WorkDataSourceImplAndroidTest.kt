package com.yeong.android_architecture_blue_print_project.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yeong.android_architecture_blue_print_project.data.Work
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkDataSourceImplAndroidTest : TestCase() {

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
        val beforeWorkList = workDao.allWork()

        val newWorkData = Work(null, "test title", "test content data")
        workDao.addWork(newWorkData)

        val afterWorkList = workDao.allWork()

        assertEquals(beforeWorkList.size + 1, afterWorkList.size)
    }

    @Test
    fun workDataBase_add_work_remove_test() = runBlocking {
        val beforeWorkList = workDao.allWork()

        val newWorkData = Work(null, "test title", "test content data")
        workDao.addWork(newWorkData)

        val removeBeforeList = workDao.allWork()

        assertEquals(beforeWorkList.size + 1, removeBeforeList.size)

        workDao.removeWork(removeBeforeList[0])
        val removeAfterListData = workDao.allWork()

        assertEquals(removeBeforeList.size - 1, removeAfterListData.size)
    }
}