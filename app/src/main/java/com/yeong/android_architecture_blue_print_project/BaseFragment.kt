package com.yeong.android_architecture_blue_print_project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.yeong.android_architecture_blue_print_project.di.InjectSavedStateViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : DaggerFragment(), HasDefaultViewModelProviderFactory {

    @Inject
    lateinit var defaultViewModelFactory: dagger.Lazy<InjectSavedStateViewModelFactory>

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
        defaultViewModelFactory.get().create(this, arguments)

    protected lateinit var viewBinding: T
        private set

    protected abstract val layoutId: Int

    abstract fun initView()
    abstract fun initBinding()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        initView()
        initBinding()
    }

    protected fun getActivityActionBar(): ActionBar? =
        (requireActivity() as AppCompatActivity).supportActionBar

}
