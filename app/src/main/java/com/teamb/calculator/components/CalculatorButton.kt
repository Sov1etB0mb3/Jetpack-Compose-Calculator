package com.teamb.calculator.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    ElevatedButton(
        modifier = modifier,
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        shape = CircleShape
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null)
        } else if (symbol != null) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = if (symbol.length > 2) 16.sp else 24.sp
                ),
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

