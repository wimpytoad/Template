package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.toadfrogson.forgetmenot.ui.theme.Primary

const val HEADER_SIZE_SMALL = 0
const val HEADER_SIZE_MEDIUM = 1
const val HEADER_SIZE_LARGE = 2

@Composable
fun Header(modifier: Modifier = Modifier, size: Int = HEADER_SIZE_MEDIUM, text: String = "", color: Color = Primary) {
    val textSize = when(size) {
        HEADER_SIZE_SMALL -> 12.sp
        HEADER_SIZE_MEDIUM -> 16.sp
        HEADER_SIZE_LARGE -> 20.sp
        else -> 16.sp
    }
    Text(text = text, color = color, fontSize = textSize, modifier = modifier.padding(start = 20.dp))
}

