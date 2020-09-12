package com.utn.appsure.di

import com.utn.appsure.viewmodel.MainListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainListViewModel() }
}