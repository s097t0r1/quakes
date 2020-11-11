package com.example.quakeapplication.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakeapplication.data.Success
import com.example.quakeapplication.data.source.QuakeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val repository: QuakeRepository
) : ViewModel() {

    private val _dataSet = MutableLiveData<Map<String, Int>>()
    val dataSet: LiveData<Map<String, Int>>
        get() = _dataSet

    private val _errorLoadingData = MutableLiveData<Boolean>()
    val errorLoadingData: LiveData<Boolean>
        get() = _errorLoadingData

    private var _currentFilterType = StatisticFilterType.MAGNITUDE_COUNT_FOR_WEEK


    init {
        setFilter(StatisticFilterType.MAGNITUDE_COUNT_FOR_WEEK)
        getStatistic()
    }

    fun setFilter(filterType: StatisticFilterType) {
        _currentFilterType = filterType
    }



    fun getStatistic() {
        viewModelScope.launch {
            val result = repository.getStatistic()

            if (result is Success) {
                _dataSet.value = when(_currentFilterType) {
                        StatisticFilterType.MAGNITUDE_COUNT_FOR_WEEK -> result.data.magnitudeCount.forWeek
                        StatisticFilterType.MAGNITUDE_COUNT_FOR_MONTH -> result.data.magnitudeCount.forMonth
                        StatisticFilterType.MAGNITUDE_COUNT_FOR_YEAR -> result.data.magnitudeCount.forYear
                }
            } else {
                _errorLoadingData.value = true
            }
        }
    }


}