package com.toadfrogson.forgetmenot.model

data class TaskModel(
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)