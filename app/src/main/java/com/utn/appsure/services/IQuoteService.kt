package com.utn.appsure.services

import android.icu.math.BigDecimal
import com.utn.appsure.model.Policy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface IQuoteService {
    @POST("quote")
    fun quote(policy: Policy): Call<QuoteResponse>
}

class QuoteResponse (
    var amount: BigDecimal
)

class QuoteService {
    private val baseUrl = "https://1861d323-f797-43ed-9056-4513bd1f1e0e.mock.pstmn.io/"

    fun Quote(policy: Policy, callback: Callback<QuoteResponse>) {
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(IQuoteService::class.java)

        service.quote(policy).enqueue(callback)
    }
}