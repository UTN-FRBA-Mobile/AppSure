package com.utn.appsure.di

import com.utn.appsure.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainListViewModel() }
    viewModel { InvoiceViewModel() }
    viewModel { CreatePolicyViewModel() }
    viewModel { CreatePolicyViewModel2() }
    viewModel { RecognizeTextViewModel() }
}