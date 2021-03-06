package com.yeong.android_architecture_blue_print_project.domain

import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import javax.inject.Inject

class AddWorkUseCase @Inject constructor(
    private val repository: WorkRepository
) : UseCase {

    suspend operator fun invoke(vararg work: Work) = repository.addWork(*work)

}