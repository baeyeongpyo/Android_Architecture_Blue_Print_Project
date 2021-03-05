package com.yeong.android_architecture_blue_print_project.ui.edit

import androidx.lifecycle.*
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import kotlinx.coroutines.launch

class EditWorkViewModel(
    private val repository: WorkRepository,
    private val resourceProvider: ResourceProvider,
    savedStateHandler: SavedStateHandle
) : ViewModel() {

    companion object {
        const val WORK_SAVE_SUCCESS = 1
    }

    val workData = savedStateHandler.get<Work>(Work.PARCEL_WORK)

    val receiveTitle = MutableLiveData<String>(workData?.title)
    val receiveContent = MutableLiveData<String>(workData?.content)

    private val _toastMessageData: MutableLiveData<String> = MutableLiveData()
    val toastMessageData: LiveData<String> = _toastMessageData

    private val _singleEvent: MutableLiveData<Int> = MutableLiveData()
    val singleEvent: LiveData<Int> = _singleEvent

    fun saveButtonEvent() {
        if (workData == null) {
            saveWork()
        } else {
            updateWork()
        }
    }

    private fun updateWork() {
        if (workData == null) return
        val titleData = receiveTitle.value
        if (titleData.isNullOrEmpty()) {
            _toastMessageData.postValue(resourceProvider.getString(R.string.edit_title_input_empty))
            return
        }
        val contentData = receiveContent.value
        if (contentData.isNullOrEmpty()) {
            _toastMessageData.postValue(resourceProvider.getString(R.string.edit_content_input_empty))
            return
        }
        workData.title = titleData
        workData.content = contentData

        viewModelScope.launch {
            repository.updateWork(workData)
            _singleEvent.postValue(WORK_SAVE_SUCCESS)
        }
    }

    private fun saveWork() {
        val titleData = receiveTitle.value
        if (titleData.isNullOrEmpty()) {
            _toastMessageData.postValue(resourceProvider.getString(R.string.edit_title_input_empty))
            return
        }
        val contentData = receiveContent.value
        if (contentData.isNullOrEmpty()) {
            _toastMessageData.postValue(resourceProvider.getString(R.string.edit_content_input_empty))
            return
        }
        val newWorkData = Work(titleData, contentData)

        viewModelScope.launch {
            repository.addWork(newWorkData)
            _singleEvent.postValue(WORK_SAVE_SUCCESS)
        }
    }
}