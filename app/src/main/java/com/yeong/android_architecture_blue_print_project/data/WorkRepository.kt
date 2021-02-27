package com.yeong.android_architecture_blue_print_project.data

interface WorkRepository {
    suspend fun getAllWork(): Result<List<Work>>
    suspend fun addWork(vararg work: Work)
    suspend fun removeWork(vararg work: Work)
}