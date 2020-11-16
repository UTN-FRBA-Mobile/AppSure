package com.utn.appsure.di

import com.utn.appsure.service.PolicyService
import com.utn.appsure.usecase.GetPoliciesUseCase
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

    single { PolicyService(get()) }
    single { GetPoliciesUseCase(get()) }
}