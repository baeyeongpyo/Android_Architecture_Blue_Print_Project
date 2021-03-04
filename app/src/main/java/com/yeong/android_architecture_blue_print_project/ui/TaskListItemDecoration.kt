package com.yeong.android_architecture_blue_print_project.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView

class TaskListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val dpSize = context.resources.displayMetrics.density

    private val dp8 = (dpSize * 8).toInt()
    private val dp6 = (dpSize * 6).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = dp6
        outRect.bottom = dp6
        outRect.left = dp8
        outRect.right = dp8
    }
}