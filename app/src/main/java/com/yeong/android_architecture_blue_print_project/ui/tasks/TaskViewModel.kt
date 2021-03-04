package com.yeong.android_architecture_blue_print_project.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onFail
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onSuccess
import kotlinx.coroutines.launch

class TaskViewModel(
    private val workRepo: WorkRepository
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Work>>()
    val tasks: LiveData<List<Work>> = _tasks

    private val _toastMessage: MutableLiveData<String> = MutableLiveData()
    val toastMessage: LiveData<String> = _toastMessage

    var taskFilter: TaskFilter = TaskFilter.ALL_WORK
        private set

    fun setFilter(filter: TaskFilter) {
        taskFilter = filter
        getTaskData()
    }

    fun getTaskData() {
        when (taskFilter) {
            TaskFilter.ALL_WORK -> getAllTasks()
            TaskFilter.YET_COMPLETE_WORK -> getYetCompleteTasks()
            TaskFilter.COMPLETE_WORK -> getCompleteTasks()
        }
    }

    private fun getAllTasks() {
        viewModelScope.launch { tasksDataPostLiveData(workRepo.getAllWork()) }
    }

    private fun getYetCompleteTasks() {
        viewModelScope.launch { tasksDataPostLiveData(workRepo.getYetCompleteWorkList()) }
    }

    private fun getCompleteTasks() {
        viewModelScope.launch { tasksDataPostLiveData(workRepo.getCompleteWorkList()) }
    }

    private fun tasksDataPostLiveData(listData: Result<List<Work>>) {
        listData.onSuccess {
            _tasks.postValue(it)
        }.onFail { _, _ ->
            _tasks.postValue(null)
        }
    }

}