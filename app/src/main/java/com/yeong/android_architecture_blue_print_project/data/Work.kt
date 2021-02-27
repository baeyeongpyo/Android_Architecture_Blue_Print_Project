package com.yeong.android_architecture_blue_print_project.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Work(
    @PrimaryKey(autoGenerate = true) val workIndex: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
)
