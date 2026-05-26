package com.teamb.calculator.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamb.calculator.CalculatorState
import com.teamb.calculator.action.CalculatorAction
import com.teamb.calculator.action.CalculatorOperation
import com.teamb.calculator.ui.theme.CalculatorTheme

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }
    val systemInDark = isSystemInDarkTheme()
    
    // Khởi tạo trạng thái Dark Mode theo hệ thống ở lần đầu tiên
    LaunchedEffect(Unit) {
        isDarkMode = systemInDark
    }

    // Tối ưu hiệu năng: Memoize các callbacks chính
    val handleAction = remember(onAction) { { action: CalculatorAction -> onAction(action) } }
    val toggleExpand = remember { { isExpanded = !isExpanded } }

    CalculatorTheme(darkTheme = isDarkMode) {
        // Sử dụng Surface bao phủ toàn bộ vùng modifier được truyền vào
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                var showMenu by remember { mutableStateOf(false) }
                
                // Hàng nút Settings
                Row(
                    modifier = Modifier.fillMaxWidth().align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Giao diện Tối")
                                        Spacer(Modifier.width(8.dp))
                                        Switch(
                                            checked = isDarkMode,
                                            onCheckedChange = { isDarkMode = it }
                                        )
                                    }
                                },
                                onClick = { }
                            )
                            DropdownMenuItem(
                                text = { Text("Lịch sử tính toán") },
                                onClick = { showMenu = false }
                            )
                        }
                    }
                }

                // Khu vực hiển thị kết quả và bàn phím
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Text(
                        text = state.expression + state.currentNumber,
                        style = MaterialTheme.typography.displayLarge,
                        maxLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp)
                            .semantics { testTag = "Result" },
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Sử dụng AnimatedContent để chuyển đổi layout mượt mà
                    AnimatedContent(
                        targetState = isExpanded,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) togetherWith 
                            fadeOut(animationSpec = tween(300))
                        },
                        label = "LayoutSwitch"
                    ) { expanded ->
                        // Đảm bảo container bao quanh lưới nút bấm sử dụng màu nền động của hệ thống
                        Surface(
                            color = MaterialTheme.colorScheme.background,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (expanded) {
                                AdvancedCalculatorPanel(
                                    onAction = handleAction,
                                    onExpandToggle = toggleExpand,
                                    buttonSpacing = buttonSpacing
                                )
                            } else {
                                StandardPanel(
                                    onAction = handleAction,
                                    onExpandToggle = toggleExpand,
                                    buttonSpacing = buttonSpacing
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StandardPanel(
    onAction: (CalculatorAction) -> Unit,
    onExpandToggle: () -> Unit,
    buttonSpacing: Dp
) {
    Column(verticalArrangement = Arrangement.spacedBy(buttonSpacing)) {
        // Hàng 1: AC, Del, ALT, /
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "AC", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Clear) }
            CalculatorButton(symbol = "Del", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Delete) }
            CalculatorButton(icon = Icons.Default.Star, modifier = Modifier.aspectRatio(1f).weight(1f), contentDescription = "Expand") { onExpandToggle() }
            CalculatorButton(symbol = "/", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
        }
        // Hàng 2: 7, 8, 9, *
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "7", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(7)) }
            CalculatorButton(symbol = "8", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(8)) }
            CalculatorButton(symbol = "9", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(9)) }
            CalculatorButton(symbol = "*", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
        }
        // Hàng 3: 4, 5, 6, -
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "4", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(4)) }
            CalculatorButton(symbol = "5", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(5)) }
            CalculatorButton(symbol = "6", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(6)) }
            CalculatorButton(symbol = "-", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
        }
        // Hàng 4: 1, 2, 3, +
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "1", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(1)) }
            CalculatorButton(symbol = "2", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(2)) }
            CalculatorButton(symbol = "3", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(3)) }
            CalculatorButton(symbol = "+", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
        }
        // Hàng 5: 0, ., =
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "0", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Number(0)) }
            CalculatorButton(symbol = ".", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Decimal) }
            CalculatorButton(symbol = "=", modifier = Modifier.aspectRatio(1f).weight(1f)) { onAction(CalculatorAction.Calculate) }
            Spacer(Modifier.weight(1f))
        }
    }
}
