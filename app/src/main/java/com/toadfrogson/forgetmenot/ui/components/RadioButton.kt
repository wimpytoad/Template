package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toadfrogson.forgetmenot.ui.theme.Primary
import com.toadfrogson.forgetmenot.ui.theme.SecondaryTextColor

@Composable
fun RadioButton(
    modifier : Modifier = Modifier,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    onClick : () -> Unit) {
    androidx.compose.material.RadioButton(
        modifier = modifier.size(24.dp),
        colors = RadioButtonDefaults.colors(
            selectedColor = Primary,
            unselectedColor = SecondaryTextColor
        ),
        selected = isSelected,
        enabled = isEnabled,
        onClick = onClick
    )
}