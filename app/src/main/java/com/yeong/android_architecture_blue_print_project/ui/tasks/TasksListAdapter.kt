package com.yeong.android_architecture_blue_print_project.ui.tasks

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yeong.android_architecture_blue_print_project.R
import com.yeong.android_architecture_blue_print_project.data.Work
import com.yeong.android_architecture_blue_print_project.ui.BaseViewHolder

class TasksListAdapter : ListAdapter<Work, TasksListAdapter.WorkViewHolder>(diffAsync) {

    companion object {

        val diffAsync = object : DiffUtil.ItemCallback<Work>() {
            override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean =
                oldItem.workIndex == newItem.workIndex

            override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
                val completeCheck = oldItem.isComplete == newItem.isComplete
                val titleCheck = oldItem.title == newItem.title
                val contentCheck = oldItem.content == newItem.content
                return completeCheck || titleCheck || contentCheck
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder =
        WorkViewHolder(R.layout.item_work, parent)

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {

        val itemData = getItem(position)

        if (itemData != null) {
            holder.completeCheckView.isChecked = itemData.isComplete
            holder.workTitleView.text = itemData.title
            holder.workContentView.text = itemData.content
        }

    }

    inner class WorkViewHolder(@LayoutRes layoutId: Int, container: ViewGroup) :
        BaseViewHolder(layoutId, container) {
        internal val completeCheckView =
            itemView.findViewById<CheckBox>(R.id.item_work_complete_check)
        internal val workTitleView = itemView.findViewById<TextView>(R.id.item_work_title)
        internal val workContentView = itemView.findViewById<TextView>(R.id.item_work_content)
    }

}