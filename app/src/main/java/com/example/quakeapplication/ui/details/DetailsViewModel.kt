package com.example.quakeapplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.data.Success
import com.example.quakeapplication.data.source.QuakeRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: QuakeRepository
) : ViewModel() {

    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean>
        get() = _loadingData

    private val _quake = MutableLiveData<Quake>()
    val quake: LiveData<Quake>
        get() = _quake

    private val _errorLoadingData = MutableLiveData<Boolean>()
    val errorLoadingData: LiveData<Boolean>
        get() = _errorLoadingData

    init {
        // getQuake()
    }

    private fun getQuake(publicID: String) {
        viewModelScope.launch {
            val result = repository.getQuake(publicID)

            if(result is Success)
                _quake.value = result.data
            else
                _errorLoadingData.value = true
        }
    }
}