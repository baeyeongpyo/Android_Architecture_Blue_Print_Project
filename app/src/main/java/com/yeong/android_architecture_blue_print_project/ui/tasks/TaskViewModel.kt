package com.yeong.android_architecture_blue_print_project.ui.tasks

import androidx.lifecycle.*
import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onFail
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onSuccess
import kotlinx.coroutines.launch

class TaskViewModel(
    private val workRepo: WorkRepository
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Work>>(null)
    val tasks: LiveData<List<Work>> = _tasks

    val isTaskListEmpty: LiveData<Boolean> =
        Transformations.map(_tasks) { it?.isNullOrEmpty() ?: true }

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
            _tasks.postValue(listOf())
        }
    }

}