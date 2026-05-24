package com.teamb.calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.teamb.calculator.action.CalculatorAction
import com.teamb.calculator.action.CalculatorOperation

@Composable
fun AdvancedCalculatorPanel(
    onAction: (CalculatorAction) -> Unit,
    onExpandToggle: () -> Unit,
    buttonSpacing: Dp
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        // Row 1: sin, cos, tan, √, x²
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(symbol = "sin", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
            CalculatorButton(symbol = "cos", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
            CalculatorButton(symbol = "tan", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
            CalculatorButton(symbol = "√", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
            CalculatorButton(symbol = "x²", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
        }
        // Row 2: π, AC, Del, /, *
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(symbol = "π", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
            CalculatorButton(symbol = "AC", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Clear)
            }
            CalculatorButton(symbol = "Del", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Delete)
            }
            CalculatorButton(symbol = "/", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
            }
            CalculatorButton(symbol = "x", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
            }
        }
        // Row 3: 7, 8, 9, -, (
        Row(
            modifier = Modifier.fillMaxWidth(),
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
            CalculatorButton(symbol = "-", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
            }
            CalculatorButton(symbol = "(", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.OpenParen)
            }
        }
        // Row 4: 4, 5, 6, +, )
        Row(
            modifier = Modifier.fillMaxWidth(),
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
            CalculatorButton(symbol = "+", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Operation(CalculatorOperation.Add))
            }
            CalculatorButton(symbol = ")", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.CloseParen)
            }
        }
        // Row 5: [ICON], 0, ., =, %
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                icon = Icons.Default.Star,
                modifier = Modifier.aspectRatio(1f).weight(1f)
            ) {
                onExpandToggle()
            }
            CalculatorButton(symbol = "0", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Number(0))
            }
            CalculatorButton(symbol = ".", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Decimal)
            }
            CalculatorButton(symbol = "=", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                onAction(CalculatorAction.Calculate)
            }
            CalculatorButton(symbol = "%", modifier = Modifier.aspectRatio(1f).weight(1f)) {
                // TODO: Implement logic here
            }
        }
    }
}
