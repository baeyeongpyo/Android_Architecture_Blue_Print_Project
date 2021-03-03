package com.yeong.android_architecture_blue_print_project.ui.tasks

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
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
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tasks_menu, menu)
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
