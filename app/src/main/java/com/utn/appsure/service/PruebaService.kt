package com.utn.appsure.service

import okhttp3.OkHttpClient

class PruebaService(client: OkHttpClient) : BaseService(client) {
    init {
        endpoint = "/prueba"
    }
}