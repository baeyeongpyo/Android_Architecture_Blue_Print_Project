package com.yeong.android_architecture_blue_print_project.ui.main

import com.yeong.android_architecture_blue_print_project.BaseActivity
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.databinding.ActivityMainBinding
import com.yeong.android_architecture_blue_print_project.ui.works.WorksFragment
import com.yeong.android_architecture_blue_print_project.util.FragmentExt.replace

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        setSupportActionBar(viewBinding.toolbar)
        supportFragmentManager.replace(R.id.fragment_container, WorksFragment::class.java)
    }

    override fun initBinding() {

    }
}