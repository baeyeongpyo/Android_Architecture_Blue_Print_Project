package com.yeong.android_architecture_blue_print_project.ui.tasks

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.FragmentResultListener
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentTaskBinding
import com.yeong.android_architecture_blue_print_project.ui.TaskListItemDecoration
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkFragment
import com.yeong.android_architecture_blue_print_project.util.FragmentExt.replaceBackStack

class TaskFragment : BaseFragment<FragmentTaskBinding>(), FragmentResultListener {

    override val layoutId: Int
        get() = R.layout.fragment_task

    private val tasksAdapter: TasksListAdapter by lazy { TasksListAdapter() }

    companion object {
        const val FRAGMENT_STACK_NAME = "tasksPage"
    }

    override fun initView() {
        viewBinding.tasKRv.addItemDecoration(TaskListItemDecoration(requireContext()))
        viewBinding.taskAddFab.setOnClickListener { addWorkPageChange() }
        viewBinding.tasKRv.adapter = tasksAdapter

        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(false)
            title = resources.getString(R.string.task_work_list)
            setHasOptionsMenu(true)
        }

        Log.i(FRAGMENT_STACK_NAME, arguments?.getString("test1") ?: "null")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tasks_menu, menu)
    }


    private fun addWorkPageChange() {
        parentFragmentManager.setFragmentResultListener(FRAGMENT_STACK_NAME, this, this)
        parentFragmentManager
            .replaceBackStack(
                R.id.fragment_container,
                FRAGMENT_STACK_NAME,
                EditWorkFragment::class.java,
                null
            )
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {

    }

    override fun initBinding() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.task_filter -> {
            popFilterMenuList()
            true
        }
        else -> false
    }

    private fun popFilterMenuList() {
        val popMenuTargetView = activity?.findViewById<View>(R.id.task_filter) ?: return
        PopupMenu(requireContext(), popMenuTargetView).run {
            menuInflater.inflate(R.menu.filter_menu, menu)
            this.setOnMenuItemClickListener(::popMenuItemClick)
            show()
        }
    }

    private fun popMenuItemClick(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        R.id.filter_all -> {
            selectOptionAllTask()
            true
        }
        R.id.filter_yet_complete -> {
            selectOptionYetComplete()
            true
        }
        R.id.filter_complete -> {
            selectOptionComplete()
            true
        }
        else -> false
    }

    private fun selectOptionAllTask() {}

    private fun selectOptionYetComplete() {}

    private fun selectOptionComplete() {}

}
