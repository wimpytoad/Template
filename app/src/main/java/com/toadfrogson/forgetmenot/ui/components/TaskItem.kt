package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.toadfrogson.forgetmenot.R
import com.toadfrogson.forgetmenot.ui.theme.PrimaryTextColor
import kotlin.time.ExperimentalTime

const val TaskStateUnchecked = false
const val TaskStateChecked = true

@OptIn(ExperimentalTime::class)
@Composable
fun TaskItem(text: String, details: String? = null, state: Boolean = TaskStateChecked) {
    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)) {
        RadioButton(modifier = Modifier.padding(end = 15.dp), isSelected = state) {}
        TextView(text = text, color = PrimaryTextColor)
    }
}