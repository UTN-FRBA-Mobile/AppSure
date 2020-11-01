package com.utn.appsure.usecase

import androidx.lifecycle.MutableLiveData
import com.utn.appsure.service.PruebaService

class PruebaUseCase(private val pruebaService: PruebaService) : BaseUseCase<String>() {
    //ToDo: inyectar el mapper y procesar el resultado
    override fun getData(liveData: MutableLiveData<String>) {
        liveData.postValue(pruebaService.get())
    }
}