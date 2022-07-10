package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun TasksList() {
    Column() {
        TaskItem(text = "first")
        TaskItem(text = "first")
        TaskItem(text = "first")
        TaskItem(text = "first")
        TaskItem(text = "first")
    }

}