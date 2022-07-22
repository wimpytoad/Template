package com.toadfrogson.forgetmenot.di

import com.toadfrogson.forgetmenot.viewmodel.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TasksViewModel(get()) }
}
