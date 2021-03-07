package com.yeong.android_architecture_blue_print_project.ui.detail

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.*
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.di.AssistedSavedStateViewModelFactory
import com.yeong.android_architecture_blue_print_project.domain.DeleteWorkUseCase
import com.yeong.android_architecture_blue_print_project.domain.UpdateWorkUseCase
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailWorkViewModel @AssistedInject constructor(
    private val updateUseCase: UpdateWorkUseCase,
    private val removeUseCase: DeleteWorkUseCase,
    private val resourceProvider: ResourceProvider,
    @Assisted savedStateHandler: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<DetailWorkViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DetailWorkViewModel
    }

    companion object {
        const val WORK_REMOVE_SUCCESS = 1
    }

    private var workData = savedStateHandler.get<Work>(Work.PARCEL_WORK)

    private val _isCompleteWork: MutableLiveData<Boolean> = MutableLiveData(workData?.isComplete)
    val isCompleteWork: LiveData<Boolean> = _isCompleteWork

    val isCompleteWorkText: LiveData<String> = Transformations.map(_isCompleteWork) {
        val resourceId = if (it == true) R.string.work_complete else R.string.work_yet_complete
        resourceProvider.getString(resourceId)
    }

    private val _workTitle: MutableLiveData<String> = MutableLiveData(workData?.title)
    val workTitle: LiveData<String> = _workTitle

    private val _workContent: MutableLiveData<String> = MutableLiveData(workData?.content)
    val workContent: LiveData<String> = _workContent

    private val _singleEvent: MutableLiveData<Int> = MutableLiveData()
    val singleEvent: LiveData<Int> = _singleEvent

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
            updateUseCase(workResult)
        }
    }

    fun removeWork() {
        val workResult = workData ?: return
        viewModelScope.launch {
            removeUseCase(workResult)
            _singleEvent.postValue(WORK_REMOVE_SUCCESS)
        }
    }

}