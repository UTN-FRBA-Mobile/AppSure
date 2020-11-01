package com.utn.appsure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.usecase.PruebaUseCase

class MainListViewModel(private val pruebaUseCase: PruebaUseCase) : ViewModel() {
    var holiwis = MutableLiveData("HOLIWISSS")

    fun getPruebaData() {
        pruebaUseCase.execute().observeForever {
            holiwis.value = it
        }
    }
}