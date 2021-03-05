package com.yeong.android_architecture_blue_print_project.ui.edit

import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.databinding.FragmentEditBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider
import com.yeong.android_architecture_blue_print_project.ui.ViewModelFactory
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkFragment
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskFragment

class EditWorkFragment : BaseFragment<FragmentEditBinding>(), HomeOptionItemSelectProvider {

    override val layoutId: Int
        get() = R.layout.fragment_edit

    private lateinit var editViewModel: WorkEditViewModel

    override fun initView() {
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.create_new_work)
            setHomeButtonEnabled(true)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    replaceTaskFragmentPage()
                }
            })

        val factory = ViewModelFactory(this, arguments)
        editViewModel = ViewModelProvider(this, factory).get(WorkEditViewModel::class.java)
    }

    private fun replaceTaskFragmentPage() {
        parentFragmentManager.popBackStack()

    }

    override fun onSelectHomeOptionItemSelect(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                replaceTaskFragmentPage()
                return true
            }
        }
        return false
    }

    override fun initBinding() {
        viewBinding.editViewModel = editViewModel
        editViewModel.toastMessageData.observe(viewLifecycleOwner) { data ->
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }
        editViewModel.singleEvent.observe(viewLifecycleOwner, ::singleEvent)
    }

    private fun singleEvent(eventCode: Int) {
        when (eventCode) {
            WorkEditViewModel.WORK_SAVE_SUCCESS -> {
                parentFragmentManager.setFragmentResult(
                    TaskFragment.FRAGMENT_STACK_NAME,
                    bundleOf(Work.PARCEL_WORK to editViewModel.workData)
                )
                parentFragmentManager.setFragmentResult(
                    DetailWorkFragment.FRAGMENT_STACK_NAME,
                    bundleOf(Work.PARCEL_WORK to editViewModel.workData)
                )
                replaceTaskFragmentPage()
            }
        }

    }

}
