package com.yeong.android_architecture_blue_print_project.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    constructor(@LayoutRes layoutId: Int, container: ViewGroup)
            : this(LayoutInflater.from(container.context).inflate(layoutId, container, false))

}