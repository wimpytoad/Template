package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.time.ExperimentalTime

const val TaskStateUnchecked = false
const val TaskStateChecked = true

@OptIn(ExperimentalTime::class)
@Composable
fun TaskItem(text: String, description: String? = null, taskState: Boolean = TaskStateChecked) {
    Column {
        var isExpanded by remember { mutableStateOf(false) }
        var taskCompleted by remember { mutableStateOf(taskState)}
        val expandContentIcon =
            if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusCheckButton(modifier = Modifier.padding(end = 15.dp), isSelected = taskCompleted) {
                taskCompleted = !taskCompleted
            }

            TextView(modifier = Modifier.weight(1.0f), text = text, color = MaterialTheme.colorScheme.secondary)

            Icon(imageVector = expandContentIcon, contentDescription = "", Modifier.clickable {
                isExpanded = !isExpanded
            })
        }

        description?.let {
            AnimatedVisibility(visible = isExpanded) {
                TextView(text = it, color = MaterialTheme.colorScheme.secondary, modifier = Modifier
                    .padding(start = 30.dp, end = 20.dp, bottom = 6.dp)
                    .fillMaxWidth()
                    .wrapContentHeight())
            }
        }

        Divider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    }

}