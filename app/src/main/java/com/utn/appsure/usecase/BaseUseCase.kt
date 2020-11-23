package com.utn.appsure.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseUseCase<T> {

    fun execute(value: Any?=null, callback: (T?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            getData(value, callback)
        }
    }


    protected fun onUiThread(block: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            block()
        }
    }

    abstract fun getData(value: Any?=null, callback: (T?) -> Unit)
}