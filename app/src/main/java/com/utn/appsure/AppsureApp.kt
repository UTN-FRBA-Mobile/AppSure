package com.utn.appsure

import android.app.Application
import com.utn.appsure.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppsureApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppsureApp)
            modules(listOf(appModule))
        }
    }
}