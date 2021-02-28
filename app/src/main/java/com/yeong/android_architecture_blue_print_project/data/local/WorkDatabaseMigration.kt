package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object WorkDatabaseMigration {

    val workDatabaseMigration1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE work ADD COLUMN isCompete BOOLEAN NOT NULL DEFAULT 'false'")
        }
    }

    val migrationList = arrayOf(workDatabaseMigration1_2)

}