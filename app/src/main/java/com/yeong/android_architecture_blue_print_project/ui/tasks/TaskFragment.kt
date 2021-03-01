package com.yeong.android_architecture_blue_print_project.ui.tasks

import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentTaskBinding
import com.yeong.android_architecture_blue_print_project.ui.TaskListItemDecoration
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkFragment
import com.yeong.android_architecture_blue_print_project.util.FragmentExt.replaceBackStack

class TaskFragment : BaseFragment<FragmentTaskBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_task

    override fun initView() {
        viewBinding.tasKRv.addItemDecoration(TaskListItemDecoration(requireContext()))
        viewBinding.taskAddFab.setOnClickListener { addWorkPageChange() }
    }

    override fun onResume() {
        super.onResume()
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(false)
            title = resources.getString(R.string.task_work_list)
        }
    }

    private fun addWorkPageChange() {
        parentFragmentManager.replaceBackStack(
            R.id.fragment_container,
            "tasksPage",
            EditWorkFragment::class.java,
            null
        )
    }

    override fun initBinding() {

    }
}