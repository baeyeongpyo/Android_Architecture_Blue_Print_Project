package com.yeong.android_architecture_blue_print_project.ui.edit

import androidx.activity.OnBackPressedCallback
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentEditBinding
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskFragment

class EditWorkFragment : BaseFragment<FragmentEditBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_edit

    override fun initView() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    replaceTaskFragmentPage()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.create_new_work)
        }
    }

    private fun replaceTaskFragmentPage() {
        parentFragmentManager
            .popBackStack()
    }

    override fun initBinding() {

    }

}
