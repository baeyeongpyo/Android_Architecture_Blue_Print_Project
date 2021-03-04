package com.yeong.android_architecture_blue_print_project.ui.edit

import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.yeong.android_architecture_blue_print_project.BaseFragment
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.FragmentEditBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider
import com.yeong.android_architecture_blue_print_project.ui.ViewModelFactory

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
        /*viewBinding.workEditDoneFab.setOnClickListener {
            parentFragmentManager.setFragmentResult(TaskFragment.FRAGMENT_STACK_NAME, bundleOf())
            parentFragmentManager.setFragmentResult(
                DetailWorkFragment.FRAGMENT_STACK_NAME,
                bundleOf()
            )
            replaceTaskFragmentPage()
        }*/
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
    }

}
