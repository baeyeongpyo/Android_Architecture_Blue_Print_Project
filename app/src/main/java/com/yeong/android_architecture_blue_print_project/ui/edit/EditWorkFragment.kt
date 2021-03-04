package com.yeong.android_architecture_blue_print_project.ui.edit

import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentEditBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkFragment
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskFragment

class EditWorkFragment : BaseFragment<FragmentEditBinding>(), HomeOptionItemSelectProvider {

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


        viewBinding.workEditDoneFab.setOnClickListener {
            parentFragmentManager.setFragmentResult(TaskFragment.FRAGMENT_STACK_NAME, bundleOf())
            parentFragmentManager.setFragmentResult(
                DetailWorkFragment.FRAGMENT_STACK_NAME,
                bundleOf()
            )
            replaceTaskFragmentPage()
        }
    }

    override fun onResume() {
        super.onResume()
        getActivityActionBar()?.run {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.create_new_work)
            setHomeButtonEnabled(true)
        }
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

    }

}
