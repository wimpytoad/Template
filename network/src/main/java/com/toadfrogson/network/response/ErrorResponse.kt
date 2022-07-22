package com.toadfrogson.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    var message: String,
    val code: Int
)