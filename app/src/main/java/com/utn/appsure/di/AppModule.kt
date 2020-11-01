package com.utn.appsure.di

import com.utn.appsure.service.PruebaService
import com.utn.appsure.usecase.PruebaUseCase
import com.utn.appsure.viewmodel.*
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { OkHttpClient() }
    viewModel { MainListViewModel(get()) }
    viewModel { InvoiceViewModel() }
    viewModel { CreatePolicyViewModel() }
    viewModel { CreatePolicyViewModel2() }
    viewModel { RecognizeTextViewModel() }

    single { PruebaService(get()) }
    single { PruebaUseCase(get()) }
}