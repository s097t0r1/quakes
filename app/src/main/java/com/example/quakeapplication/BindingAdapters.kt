package com.example.quakeapplication

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
fun setRefreshListener(view: SwipeRefreshLayout, clickListener: () -> Unit) {
    clickListener.let {
        view.setOnRefreshListener {
            clickListener()
            view.isRefreshing = false
        }
    }
}