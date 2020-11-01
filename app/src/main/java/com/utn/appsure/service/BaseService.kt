package com.utn.appsure.service

import com.utn.appsure.BuildConfig
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


abstract class BaseService(val client: OkHttpClient) {
    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    lateinit var endpoint: String

    @Throws(IOException::class)
    fun post(json: String): String? {
        val body: RequestBody = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url("${BuildConfig.BASE_URL}$endpoint")
            .post(body)
            .build()
        val response = client.newCall(request).execute()
        return if (response.isSuccessful) response.body?.string() else ""
    }

    @Throws(IOException::class)
    fun get(): String? {
        val request: Request = Request.Builder()
            .url("${BuildConfig.BASE_URL}$endpoint")
            .build()
        val response = client.newCall(request).execute()
        return if (response.isSuccessful) response.body?.string() else ""
    }
}