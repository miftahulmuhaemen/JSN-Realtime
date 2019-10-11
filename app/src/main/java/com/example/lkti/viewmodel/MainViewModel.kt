package com.example.lkti.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lkti.model.Data

class MainViewModel(private val dataRepository : DataRepository = DataRepository()) : ViewModel() {

    private val fetchRealtimeData: LiveData<List<Data>> get() = dataRepository.getRealtimeData()
    private val fetchBulkData: LiveData<List<Data>> get() = dataRepository.getBulkData()
    val getRealtimeData: LiveData<List<Data>> get() = dataRepository.liveRealtimeData
    val getBulkData: LiveData<List<Data>> get() = dataRepository.liveBulkData

    fun onStart(){
        fetchBulkData
        fetchRealtimeData
    }

    override fun onCleared() {
        super.onCleared()
        dataRepository.completableJob.cancel()
    }
}