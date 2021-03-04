package com.yeong.android_architecture_blue_print_project.ui.detail

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.data.Work.Companion.PARCEL_WORK
import com.yeong.android_architecture_blue_print_project.databinding.FragmentTaskDetailBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider

class DetailWorkFragment : BaseFragment<FragmentTaskDetailBinding>(), HomeOptionItemSelectProvider {

    override val layoutId: Int
        get() = R.layout.fragment_task_detail

    override fun initView() {
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.task_work_detail)
            setHasOptionsMenu(true)
        }

        val workData = arguments?.getParcelable<Work>(PARCEL_WORK)

        // FIXME
        workData?.run {
            viewBinding.workDetailCheck.isChecked = isComplete
            viewBinding.workDetailTitle.text = title
            viewBinding.workDetailContent.text = content
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_detail_option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.work_edit -> {
                // TODO
                true
            }

            R.id.work_delete -> {
                // TODO
                true
            }
            else -> false
        }

    override fun onSelectHomeOptionItemSelect(item: MenuItem): Boolean {
        parentFragmentManager.popBackStack()
        return true
    }

    override fun initBinding() {

    }
}