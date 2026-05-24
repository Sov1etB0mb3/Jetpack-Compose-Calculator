package com.teamb.calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamb.calculator.CalculatorState
import com.teamb.calculator.action.CalculatorAction
import com.teamb.calculator.action.CalculatorOperation

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {

            Text(
                text = state.number1 + state.operation?.operator.orEmpty() + state.number2,
                style = MaterialTheme.typography.displayLarge,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onBackground
            )

            if (isExpanded) {
                AdvancedCalculatorPanel(
                    onAction = onAction,
                    onExpandToggle = { isExpanded = !isExpanded },
                    buttonSpacing = buttonSpacing
                )
            } else {
                // Standard 4-column Layout
                // Row 1: AC, Del, ALT, /
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(symbol = "AC", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Clear)
                    }
                    CalculatorButton(symbol = "Del", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Delete)
                    }
                    CalculatorButton(icon = Icons.Default.Star, modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        isExpanded = !isExpanded
                    }
                    CalculatorButton(symbol = "/", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                }

                // Row 2: 7, 8, 9, x
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(symbol = "7", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(7))
                    }
                    CalculatorButton(symbol = "8", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(8))
                    }
                    CalculatorButton(symbol = "9", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(9))
                    }
                    CalculatorButton(symbol = "x", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                }

                // Row 3: 4, 5, 6, -
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(symbol = "4", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(4))
                    }
                    CalculatorButton(symbol = "5", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(5))
                    }
                    CalculatorButton(symbol = "6", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(6))
                    }
                    CalculatorButton(symbol = "-", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                }

                // Row 4: 1, 2, 3, +
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(symbol = "1", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(1))
                    }
                    CalculatorButton(symbol = "2", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(2))
                    }
                    CalculatorButton(symbol = "3", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(3))
                    }
                    CalculatorButton(symbol = "+", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                }

                // Row 5: 0, ., =, (Spacer)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(symbol = "0", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Number(0))
                    }
                    CalculatorButton(symbol = ".", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Decimal)
                    }
                    CalculatorButton(symbol = "=", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                        onAction(CalculatorAction.Calculate)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
