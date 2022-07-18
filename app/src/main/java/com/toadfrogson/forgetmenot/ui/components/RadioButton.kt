package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusCheckButton(
    modifier : Modifier = Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    onClick : () -> Unit) {
    RadioButton(
        modifier = modifier.size(24.dp),
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.secondary,
            unselectedColor = MaterialTheme.colorScheme.secondary
        ),
        selected = isSelected,
        enabled = isEnabled,
        onClick = onClick
    )
}