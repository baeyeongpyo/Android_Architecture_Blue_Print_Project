package com.yeong.android_architecture_blue_print_project.ui.detail

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.*
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import kotlinx.coroutines.launch

class DetailWorkViewModel(
    private val repository: WorkRepository,
    private val resourceProvider: ResourceProvider,
    savedStateHandler: SavedStateHandle
) : ViewModel() {

    private var workData = savedStateHandler.get<Work>(Work.PARCEL_WORK)

    private val _isCompleteWork: MutableLiveData<Boolean> = MutableLiveData(workData?.isComplete)
    val isCompleteWork: LiveData<Boolean> = _isCompleteWork

    val isCompleteWorkText = Transformations.map(_isCompleteWork) {
        val resourceId = if (it == true) R.string.work_complete else R.string.work_yet_complete
        resourceProvider.getString(resourceId)
    }

    private val _workTitle: MutableLiveData<String> = MutableLiveData(workData?.title)
    val workTitle: LiveData<String> = _workTitle

    private val _workContent: MutableLiveData<String> = MutableLiveData(workData?.content)
    val workContent: LiveData<String> = _workContent

    fun changeWork(work: Work) {
        workData = work
        _isCompleteWork.postValue(work.isComplete)
        _workTitle.postValue(work.title)
        _workContent.postValue(work.content)
    }

    fun workCompleteClick(view: View) {
        if (view is CheckBox) workCompleteUpdate(view.isChecked)
    }

    private fun workCompleteUpdate(b: Boolean) {
        val workResult = workData ?: return

        workResult.isComplete = b
        viewModelScope.launch {
            repository.updateWork(workResult)
        }
    }

}