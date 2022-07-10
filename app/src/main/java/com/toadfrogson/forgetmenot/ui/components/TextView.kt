package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun TextView(modifier: Modifier = Modifier,
             text: String,
             textAlign: TextAlign = TextAlign.Start,
             color: Color,
             overflow: TextOverflow = TextOverflow.Clip,
             maxLines: Int = Int.MAX_VALUE,
             onClick: (() -> Unit)? = null) {

    var textViewModifier = modifier

    if (onClick != null) {
        textViewModifier = textViewModifier.clickable(onClick = onClick)
    }

    Text(
        modifier = textViewModifier,
        text = text,
        textAlign = textAlign,
        color = color,
        overflow = overflow,
        maxLines = maxLines
    )
}