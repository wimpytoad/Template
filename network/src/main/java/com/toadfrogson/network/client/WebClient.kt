package com.toadfrogson.network.client

import com.toadfrogson.network.response.ApiResponse
import com.toadfrogson.network.response.ErrorResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.time.ExperimentalTime

class WebClientException(val statusCode: HttpStatusCode) : Exception()

class WebClient{

    @OptIn(ExperimentalSerializationApi::class)
    @ExperimentalTime
    suspend inline fun <reified T : Any> makeClientGet(endpoint: String): ApiResponse<T> {

        val url = "" + endpoint //baseUrl goes here

        try {
            val client = getClient() {
                throw WebClientException(it)
            }
            val response =  client.use {
                it.get(url)
            }

            println("GET '$url' SUCCESS")
            return ApiResponse(true, response as T, null)
        } catch (ex: Exception) {
            return when (ex) {
                is WebClientException -> ApiResponse(false, null,
                    ErrorResponse("", ex.statusCode.value))
                else -> {
                    println("GET '$url' FAILED: '${ex.message}'")
                    ApiResponse(true, null, null)
                }
            }
        }
    }

    @ExperimentalTime
    @ExperimentalSerializationApi
    @PublishedApi
    internal fun getClient(httpResponse: (HttpStatusCode) -> Unit): HttpClient {
        return HttpClient(CIO) {

            install(HttpTimeout) {
                requestTimeoutMillis = 6000
            }

            install(ContentNegotiation) {
                json( Json {
                    prettyPrint = true
                    explicitNulls = false
                    ignoreUnknownKeys = true
                })
            }


            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val exceptionResponse = (exception as? ClientRequestException)?.response
                    exceptionResponse?.status?.let { statusCode ->
                        httpResponse(statusCode)
                    }
                }
            }
        }

    }
}