package com.teamb.calculator.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    
    ElevatedButton(
        modifier = modifier,
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        shape = CircleShape,
        // 1. Đồng bộ màu sắc với MaterialTheme để hỗ trợ Dark/Light mode tự động
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        // 2. Loại bỏ Padding mặc định để tránh ép chữ trên màn hình nhỏ
        contentPadding = PaddingValues(0.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        } else if (symbol != null) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = if (symbol.length > 2) 16.sp else 24.sp
                ),
                maxLines = 1,
                softWrap = false,
                textAlign = TextAlign.Center
            )
        }
    }
}
