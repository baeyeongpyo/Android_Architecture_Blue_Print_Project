package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object WorkDatabaseMigration {

    @JvmStatic
    val workDatabaseMigration1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE 'Work' ADD COLUMN 'isComplete' BOOLEAN NOT NULL DEFAULT '0'")
        }
    }

    @JvmStatic
    val workDatabaseMigration2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE 'Work' ADD COLUMN 'workCreateDate' INTEGER NOT NULL DEFAULT 0")
        }
    }

    val migrationList = arrayOf(workDatabaseMigration1_2, workDatabaseMigration2_3)

}