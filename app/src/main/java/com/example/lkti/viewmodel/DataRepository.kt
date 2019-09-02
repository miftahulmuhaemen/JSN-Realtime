package com.example.lkti.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.lkti.model.Data
import com.example.lkti.network.RetrofitFactory
import kotlinx.coroutines.*
import retrofit2.HttpException

class DataRepository {

            val completableJob = Job()
    private var mutableLiveData = MutableLiveData<List<Data>>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val thisApiCorService by lazy {
        RetrofitFactory.makeRetrofitService()
    }

    fun getMutableLiveData(): MutableLiveData<List<Data>> {
        coroutineScope.launch {
            val request = thisApiCorService.getData()
            withContext(Dispatchers.Main) {
                try {
                    if (request.isSuccessful)
                        mutableLiveData.value = request.body()
                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return mutableLiveData
    }


}