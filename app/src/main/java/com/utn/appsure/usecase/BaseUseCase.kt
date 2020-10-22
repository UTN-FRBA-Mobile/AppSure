package com.utn.appsure.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseUseCase<T> {

    fun execute(): LiveData<T> {
        val data = MutableLiveData<T>()
        CoroutineScope(Dispatchers.IO).launch {
            getData(data)
        }
        return data
    }

    abstract fun getData(liveData: MutableLiveData<T>)
}