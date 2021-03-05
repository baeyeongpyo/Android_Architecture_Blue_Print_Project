package com.yeong.android_architecture_blue_print_project.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.Work.Companion.PARCEL_WORK
import com.yeong.android_architecture_blue_print_project.databinding.FragmentTaskDetailBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider
import com.yeong.android_architecture_blue_print_project.ui.ViewModelFactory
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkViewModel.Companion.WORK_REMOVE_SUCCESS
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkFragment
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskFragment
import com.yeong.android_architecture_blue_print_project.util.FragmentExt.replaceBackStack

class DetailWorkFragment : BaseFragment<FragmentTaskDetailBinding>(), HomeOptionItemSelectProvider,
    FragmentResultListener {

    companion object {
        const val FRAGMENT_STACK_NAME = "detailPage"
    }

    override val layoutId: Int
        get() = R.layout.fragment_task_detail

    private lateinit var workData: Work
    private lateinit var detailViewModel: DetailWorkViewModel

    override fun initView() {
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.task_work_detail)
            setHasOptionsMenu(true)
        }

        val tempWorkData = arguments?.getParcelable<Work>(PARCEL_WORK)

        if (tempWorkData == null) {
            // error
            parentFragmentManager.popBackStack()
        } else {
            workData = tempWorkData
        }

        val factory = ViewModelFactory(this, arguments)
        detailViewModel = ViewModelProvider(this, factory).get(DetailWorkViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_detail_option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.work_edit -> {
                parentFragmentManager.setFragmentResultListener(FRAGMENT_STACK_NAME, this, this)
                parentFragmentManager.replaceBackStack(
                    R.id.fragment_container,
                    FRAGMENT_STACK_NAME,
                    EditWorkFragment::class.java,
                    bundleOf(PARCEL_WORK to workData)
                )
                true
            }

            R.id.work_delete -> {
                detailViewModel.removeWork()
                true
            }
            else -> false
        }

    override fun onSelectHomeOptionItemSelect(item: MenuItem): Boolean {
        parentFragmentManager.popBackStack()
        return true
    }

    override fun initBinding() {
        viewBinding.detailViewModel = detailViewModel

        detailViewModel.singleEvent.observe(viewLifecycleOwner, ::singleEvent)
    }

    private fun singleEvent(eventCode: Int) {
        when (eventCode) {
            WORK_REMOVE_SUCCESS -> {
                parentFragmentManager.setFragmentResult(TaskFragment.FRAGMENT_STACK_NAME, bundleOf())
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        when (requestKey) {
            FRAGMENT_STACK_NAME -> {
                arguments = (arguments ?: bundleOf()).apply { putAll(result) }
                val work = result.getParcelable<Work>(PARCEL_WORK)
                if (work != null) detailViewModel.changeWork(work)
            }
        }
    }
}