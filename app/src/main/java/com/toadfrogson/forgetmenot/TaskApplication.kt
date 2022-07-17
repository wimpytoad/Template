package com.toadfrogson.forgetmenot

import android.app.Application
import com.toadfrogson.forgetmenot.data.DataModule.Companion.dataModule
import com.toadfrogson.forgetmenot.di.AppModule.Companion.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class TaskApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TaskApplication)
            modules(listOf(viewModelModule, dataModule))
        }
    }
}