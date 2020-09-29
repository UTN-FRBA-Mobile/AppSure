package com.utn.appsure.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.utn.appsure.R

class MainListViewModel : ViewModel() {
    val holiwis = "HOLIWISSS"

    fun onFloatingButtonClick(v: View) {
        v.findNavController().navigate(R.id.action_main_list_to_invoice)
    }
}