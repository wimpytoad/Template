package com.toadfrogson.forgetmenot.di

import com.toadfrogson.forgetmenot.data.TaskDB
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepo
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepoImpl
import com.toadfrogson.forgetmenot.viewmodel.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

class AppModule {
    companion object {

        val viewModelModule = module {
            viewModel { TasksViewModel(get()) }
        }

    }
}