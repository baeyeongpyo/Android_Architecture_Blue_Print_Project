package com.yeong.android_architecture_blue_print_project.ui.main

import com.yeong.android_architecture_blue_print_project.BaseActivity
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.ActivityMainBinding
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskFragment
import com.yeong.android_architecture_blue_print_project.util.FragmentExt.replace

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        setSupportActionBar(viewBinding.toolbar)
        supportFragmentManager.replace(R.id.fragment_container, TaskFragment::class.java)
    }

    override fun initBinding() {

    }
}