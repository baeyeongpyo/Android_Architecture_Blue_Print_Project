package com.yeong.android_architecture_blue_print_project.ui.tasks

import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentTaskBinding
import com.yeong.android_architecture_blue_print_project.ui.TaskListItemDecoration

class TaskFragment : BaseFragment<FragmentTaskBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_task

    override fun initView() {
        viewBinding.tasKRv.addItemDecoration(TaskListItemDecoration(requireContext()))

        getActivityActionBar()?.title = resources.getString(R.string.task_work_list)
    }

    override fun initBinding() {

    }
}