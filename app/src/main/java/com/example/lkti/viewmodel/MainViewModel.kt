package com.example.lkti.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.lkti.model.Data

class MainViewModel(private val dataRepository : DataRepository = DataRepository()) : ViewModel() {


    val allData: LiveData<List<Data>> get() = dataRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        dataRepository.completableJob.cancel()
    }
}