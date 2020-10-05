package com.utn.appsure.di

import com.utn.appsure.viewmodel.CreatePolicyViewModel
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import com.utn.appsure.viewmodel.InvoiceViewModel
import com.utn.appsure.viewmodel.MainListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainListViewModel() }
    viewModel { InvoiceViewModel() }
    viewModel { CreatePolicyViewModel() }
    viewModel { CreatePolicyViewModel2() }
}