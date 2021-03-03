package com.yeong.android_architecture_blue_print_project.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Work(
    @PrimaryKey(autoGenerate = true) val workIndex: Int?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "isComplete") var isComplete: Boolean = false, // added in version 2
    @ColumnInfo(name = "workCreateDate") var workCreateDate: Long = System.currentTimeMillis() // added in version 3
) : Parcelable {
    constructor(title: String, content: String) : this(
        null,
        title,
        content,
        false,
        System.currentTimeMillis()
    )
}
