package com.example.quakeapplication.ui.quakes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakeapplication.network.QuakeApi
import kotlinx.coroutines.launch

import java.lang.Exception

class QuakesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is not home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        getQuakes()

    }

    private fun getQuakes() {
        viewModelScope.launch {
            try {
                _text.value = QuakeApi.retrofitService.getQuakes(5)[0].properties.publicID
            } catch (E: Exception) {
                E.message
            }

        }

    }
}