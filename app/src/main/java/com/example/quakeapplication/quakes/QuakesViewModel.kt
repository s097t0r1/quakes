package com.example.quakeapplication.quakes

import android.util.Log
import androidx.lifecycle.*
import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.data.Success
import com.example.quakeapplication.data.source.QuakeRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class QuakesViewModel @Inject constructor(
    private val repository: QuakeRepository
) : ViewModel() {

    private val _quakesList = MutableLiveData<List<Quake>>()
    val quakesList: LiveData<List<Quake>>
        get() = _quakesList

    private val _filterValue = MutableLiveData<Int>()
    val filterValue: LiveData<Int>
        get() = _filterValue

    private val _errorLoadingData = MutableLiveData<Boolean>()
    val errorLoadingData: LiveData<Boolean>
        get() = _errorLoadingData

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean>
        get() = _isDataLoading

    init {
        setFilterMMI(3)
        getQuakes()
    }

    fun setFilterMMI(filterMMI: Int) {
        if(filterMMI in 0..10) {
            _filterValue.value = filterMMI
            getQuakes()
        }
        else
            throw IllegalArgumentException("Illegal value of MMI")
    }

    fun getQuakes(forceUpdate: Boolean = false) {

        _isDataLoading.value = true

        viewModelScope.launch {

            val quakesResult = repository.getQuakes(_filterValue.value!!, forceUpdate)

            if(quakesResult is Success)
                _quakesList.value = quakesResult.data
            else {
                _quakesList.value = _quakesList.value ?: emptyList()
                _errorLoadingData.value = true
            }
            _isDataLoading.value = false
        }


    }

    fun onErrorLoadingDataComplete() {
        _errorLoadingData.value = false
    }

}