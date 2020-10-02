package com.example.quakeapplication.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class StatisticsViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    fun test() {
        Timber.d("Test view model")
    }

    val text: LiveData<String> = _text
}