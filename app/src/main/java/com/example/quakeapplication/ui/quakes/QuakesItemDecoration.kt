package com.example.quakeapplication.ui.quakes

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class QuakesItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(offset, offset, offset, offset)
    }

}