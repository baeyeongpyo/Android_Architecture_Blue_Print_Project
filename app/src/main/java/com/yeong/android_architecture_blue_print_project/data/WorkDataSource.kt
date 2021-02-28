package com.yeong.android_architecture_blue_print_project.data

interface WorkDataSource {
    suspend fun getAllWork(): Result<List<Work>>
    suspend fun getYetCompleteWorkList(): Result<List<Work>>
    suspend fun getCompleteWorkList(): Result<List<Work>>
    suspend fun addWork(vararg work: Work)
    suspend fun removeWork(vararg work: Work)
    suspend fun updateWork(vararg work: Work)
}
