package com.example.quakeapplication.domain.quakes

import android.app.Application
import androidx.lifecycle.*
import com.example.quakeapplication.network.QuakeApi
import kotlinx.coroutines.launch

import java.lang.Exception

class QuakesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>("This is home fragment")
    val text: LiveData<String> = _text

    init {
        getQuakes()
    }

    private fun getQuakes() {
        viewModelScope.launch {
            try {
                //_text.value = QuakeApi.retrofitService.getQuakes(5)[0].properties.publicID
            } catch (E: Exception) {
                E.message
            }

        }

    }
}