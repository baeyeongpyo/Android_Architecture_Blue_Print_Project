package com.yeong.android_architecture_blue_print_project

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yeong.android_architecture_blue_print_project.ui.HomeOptionItemSelectProvider
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

    protected lateinit var viewBinding: T
        private set

    protected abstract val layoutId: Int

    abstract fun initView()
    abstract fun initBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.lifecycleOwner = this
        initView()
        initBinding()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportFragmentManager.fragments
                .filter { it.isVisible }
                .filter { it is HomeOptionItemSelectProvider }
                .map { it as HomeOptionItemSelectProvider }
                .forEach {
                    if (it.onSelectHomeOptionItemSelect(item)) return true
                }
        }
        return super.onOptionsItemSelected(item)
    }
}