package com.yeong.android_architecture_blue_print_project.ui

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

class TaskListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val dpSize = context.resources.displayMetrics.density

    private val dp8 = (dpSize * 8).toInt()
    private val dp6 = (dpSize * 6).toInt()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val childCount = parent.childCount

        for (i in 0..childCount) {
            val view = parent.getChildAt(i)
            view.setPadding(dp8, dp6, dp8, dp6)
        }

    }
}