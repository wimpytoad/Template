package com.toadfrogson.network.di

import com.toadfrogson.network.client.WebClient
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalTime
val networkModule = module {
    factory { WebClient() }
}
