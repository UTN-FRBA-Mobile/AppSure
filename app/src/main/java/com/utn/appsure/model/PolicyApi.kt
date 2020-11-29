package com.utn.appsure.model

import com.utn.appsure.R

class PolicyApi {
    fun getPolicies(): MutableList<Policy> {
        val dataset = mutableListOf<Policy>()

        for (i in 0..30) {

            dataset.add(Policy( "Policy $i","Marca","Modelo",2020,"Color", R.drawable.car, lat = -34.0, lon = 151.0))

        }
        return dataset
    }
}

