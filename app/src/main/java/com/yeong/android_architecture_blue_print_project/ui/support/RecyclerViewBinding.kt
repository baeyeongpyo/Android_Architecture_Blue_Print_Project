package com.yeong.android_architecture_blue_print_project.ui.support

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBinding {

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("listItem")
    @JvmStatic
    fun <T> setListItem(rv: RecyclerView, list: List<T>?) {
        if (list == null) return
        val adapter = rv.adapter as? ListAdapter<T, *>
        adapter?.submitList(list)
    }

}