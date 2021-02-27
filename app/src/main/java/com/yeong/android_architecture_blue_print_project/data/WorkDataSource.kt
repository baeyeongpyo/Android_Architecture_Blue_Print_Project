package com.yeong.android_architecture_blue_print_project.data

import com.yeong.android_architecture_blue_print_project.data.local.Work

interface WorkDataSource {
    suspend fun getAllWork() : List<Work>
    suspend fun insertWork(vararg work : Work)
    suspend fun deleteWork(vararg work : Work)
}
