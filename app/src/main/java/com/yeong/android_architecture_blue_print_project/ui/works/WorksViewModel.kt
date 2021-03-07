package com.yeong.android_architecture_blue_print_project.ui.works

import androidx.lifecycle.*
import com.yeong.android_architecture_blue_print_project.data.Result
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.domain.AllWorksUseCase
import com.yeong.android_architecture_blue_print_project.domain.CompleteWorksUseCase
import com.yeong.android_architecture_blue_print_project.domain.UpdateWorkUseCase
import com.yeong.android_architecture_blue_print_project.domain.YetCompleteWorksUseCase
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onFail
import com.yeong.android_architecture_blue_print_project.util.ResultExt.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorksViewModel @Inject constructor(
    private val allWorksUseCase: AllWorksUseCase,
    private val yetCompleteUseCase : YetCompleteWorksUseCase,
    private val completeUseCase : CompleteWorksUseCase,
    private val updateUseCase : UpdateWorkUseCase
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Work>>(null)
    val tasks: LiveData<List<Work>> = _tasks

    val isTaskListEmpty: LiveData<Boolean> =
        Transformations.map(_tasks) { it?.isNullOrEmpty() ?: true }

    private val _taskFilterLiveData: MutableLiveData<WorkFilter> = MutableLiveData()
    val taskFilterLiveData: LiveData<WorkFilter> = _taskFilterLiveData

    var taskFilter: WorkFilter = WorkFilter.ALL_WORK
        private set

    init {
        setFilter(WorkFilter.ALL_WORK)
    }

    fun setFilter(filter: WorkFilter) {
        taskFilter = filter
        _taskFilterLiveData.postValue(filter)
        getTaskData()
    }

    fun getTaskData() {
        when (taskFilter) {
            WorkFilter.ALL_WORK -> getAllTasks()
            WorkFilter.YET_COMPLETE_WORK -> getYetCompleteTasks()
            WorkFilter.COMPLETE_WORK -> getCompleteTasks()
        }
    }

    private fun getAllTasks() {
        viewModelScope.launch { tasksDataPostLiveData(allWorksUseCase()) }
    }

    private fun getYetCompleteTasks() {
        viewModelScope.launch { tasksDataPostLiveData(yetCompleteUseCase()) }
    }

    private fun getCompleteTasks() {
        viewModelScope.launch { tasksDataPostLiveData(completeUseCase()) }
    }

    private fun tasksDataPostLiveData(listData: Result<List<Work>>) {
        listData.onSuccess {
            _tasks.postValue(it)
        }.onFail { _, _ ->
            _tasks.postValue(listOf())
        }
    }

    fun workCompleteDataUpdate(work: Work, b: Boolean) {
        work.isComplete = b
        viewModelScope.launch {
            updateUseCase()
        }
    }

}