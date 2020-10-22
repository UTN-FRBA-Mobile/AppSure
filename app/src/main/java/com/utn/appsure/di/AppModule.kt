package com.utn.appsure.di

import com.utn.appsure.service.PruebaService
import com.utn.appsure.usecase.PruebaUseCase
import com.utn.appsure.viewmodel.CreatePolicyViewModel
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import com.utn.appsure.viewmodel.InvoiceViewModel
import com.utn.appsure.viewmodel.MainListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { OkHttpClient() }
    viewModel { MainListViewModel(get()) }
    viewModel { InvoiceViewModel() }
    viewModel { CreatePolicyViewModel() }
    viewModel { CreatePolicyViewModel2() }

    single { PruebaService(get()) }
    single { PruebaUseCase(get()) }
}