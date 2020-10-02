package com.example.quakeapplication

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import timber.log.Timber

@BindingAdapter("onRefresh")
fun setRefreshListener(view: SwipeRefreshLayout, clickListener: () -> Unit) {
    clickListener.let {
        view.setOnRefreshListener {
            clickListener()
        }
    }
}

@BindingAdapter("isRefreshing")
fun setIsRefreshing(view: SwipeRefreshLayout, isDataLoading: Boolean?) {
    isDataLoading?.let {
        view.isRefreshing = isDataLoading
        Timber.d(isDataLoading.toString())
    }
}