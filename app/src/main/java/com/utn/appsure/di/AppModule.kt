package com.utn.appsure.di

import com.utn.appsure.db.AppSureDatabase
import com.utn.appsure.db.PolicyRepository
import com.utn.appsure.service.PolicyService
import com.utn.appsure.usecase.CreatePolicyUseCase
import com.utn.appsure.usecase.GetPoliciesUseCase
import com.utn.appsure.usecase.GetPolicyUseCase
import com.utn.appsure.viewmodel.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { OkHttpClient() }
    single { AppSureDatabase.getAppDataBase(androidContext()) }
    viewModel { MainListViewModel(get()) }
    viewModel { CreatePolicyViewModel() }
    viewModel { CreatePolicyViewModel2(get()) }
    viewModel { RecognizeTextViewModel() }
    viewModel { MapViewModel(get()) }
    viewModel { PolicyDetailViewModel(get()) }

    single { PolicyService(get()) }
    single { PolicyRepository(get()) }
    single { GetPoliciesUseCase(get(), get()) }
    single { CreatePolicyUseCase(get(), get()) }
    single { GetPolicyUseCase(get()) }
}