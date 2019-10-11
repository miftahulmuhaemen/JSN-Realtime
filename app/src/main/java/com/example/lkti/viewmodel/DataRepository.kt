package com.example.lkti.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.lkti.model.Data
import com.example.lkti.network.RetrofitFactory
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.HttpException
import java.util.*
import kotlin.concurrent.schedule

class DataRepository : AnkoLogger {

    val completableJob = Job()
    var liveRealtimeData = MutableLiveData<List<Data>>()
    var liveBulkData = MutableLiveData<List<Data>>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val thisApiCorService by lazy {
        RetrofitFactory.makeRetrofitService()
    }

    fun getRealtimeData(): MutableLiveData<List<Data>> {
        Timer("getRealtimeData", false).schedule(3000, 3000) {
            coroutineScope.launch {
                val request = thisApiCorService.realtimeData()
                withContext(Dispatchers.Main) {
                    try {
                        if (request.isSuccessful)
                            liveRealtimeData.value = request.body()
                    } catch (e: HttpException) {
                        error(e)
                    } catch (e: Throwable) {
                        error(e)
                    }
                }
            }
        }
        return liveRealtimeData
    }

    fun getBulkData(): MutableLiveData<List<Data>> {
        coroutineScope.launch {
            val request = thisApiCorService.bulkData()
            withContext(Dispatchers.Main) {
                try {
                    if (request.isSuccessful) {
                        liveBulkData.value = request.body()
                    }
                } catch (e: HttpException) {
                    error(e)
                } catch (e: Throwable) {
                    error(e)
                }
            }
        }
        return liveBulkData
    }

}