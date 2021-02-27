package com.yeong.android_architecture_blue_print_project.data

interface WorkDataSource {
    suspend fun getAllWork(): Result<List<Work>>
    suspend fun insertWork(vararg work: Work)
    suspend fun deleteWork(vararg work: Work)
}
