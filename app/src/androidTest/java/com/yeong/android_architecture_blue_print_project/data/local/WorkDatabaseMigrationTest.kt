package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.yeong.android_architecture_blue_print_project.data.Work
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkDatabaseMigrationTest : TestCase() {

    private val TEST_DB = "migration_test_db"

    @JvmField
    @Rule
    var testHelper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        WorkDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrationTo_1_x() {
        testHelper.createDatabase(TEST_DB, 1).apply { close() }

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            WorkDatabase::class.java,
            TEST_DB
        ).addMigrations(*WorkDatabaseMigration.migrationList)
            .build().apply {
                openHelper.writableDatabase
                close()
            }
    }

}