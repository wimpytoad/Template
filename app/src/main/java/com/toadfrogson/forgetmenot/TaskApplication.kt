package com.toadfrogson.forgetmenot

import android.app.Application
import com.toadfrogson.forgetmenot.data.dataModule
import com.toadfrogson.forgetmenot.di.viewModelModule
import com.toadfrogson.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import kotlin.time.ExperimentalTime

class TaskApplication : Application(), KoinComponent {
    @OptIn(ExperimentalTime::class)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TaskApplication)
            modules(listOf(viewModelModule, dataModule, networkModule))
        }
    }
}