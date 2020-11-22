package com.utn.appsure.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseUseCase<T> {

    fun execute(callback: (T?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            getData(callback)
        }
    }

    protected fun onUiThread(block: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            block()
        }
    }

    abstract fun getData(callback: (T?) -> Unit)
}