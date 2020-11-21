package com.utn.appsure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecognizeTextViewModel : ViewModel() {

    val resultRecognizeText = MutableLiveData<String>()

    init{
        resultRecognizeText.value=""
    }

    fun changeResult(newResult: String){
        if(!newResult.isNullOrBlank()){
            resultRecognizeText.value=newResult
        }
    }


}