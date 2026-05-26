package com.teamb.calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    // Tối ưu hiệu năng: Memoize các callbacks
    val handleAction = remember(onAction) { { action: CalculatorAction -> onAction(action) } }
    val handleExpand = remember(onExpandToggle) { { onExpandToggle() } }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        // Hàng 1: sin, cos, tan, √, x²
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "sin", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Sin) }
            CalculatorButton(symbol = "cos", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Cos) }
            CalculatorButton(symbol = "tan", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Tan) }
            CalculatorButton(symbol = "√", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Sqrt) }
            CalculatorButton(symbol = "x²", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Square) }
        }
        // Hàng 2: π, (, ), !, /
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "π", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Pi) }
            CalculatorButton(symbol = "(", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.OpenParen) }
            CalculatorButton(symbol = ")", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.CloseParen) }
            CalculatorButton(symbol = "!", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Factorial) }
            CalculatorButton(symbol = "/", modifier = Modifier.aspectRatio(1f).weight(1f)) { 
                handleAction(CalculatorAction.Operation(CalculatorOperation.Divide)) 
            }
        }
        // Hàng 3: 7, 8, 9, AC, Del
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "7", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(7)) }
            CalculatorButton(symbol = "8", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(8)) }
            CalculatorButton(symbol = "9", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(9)) }
            CalculatorButton(symbol = "AC", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Clear) }
            CalculatorButton(symbol = "Del", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Delete) }
        }
        // Hàng 4: 4, 5, 6, *, -
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "4", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(4)) }
            CalculatorButton(symbol = "5", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(5)) }
            CalculatorButton(symbol = "6", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(6)) }
            CalculatorButton(symbol = "*", modifier = Modifier.aspectRatio(1f).weight(1f)) { 
                handleAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) 
            }
            CalculatorButton(symbol = "-", modifier = Modifier.aspectRatio(1f).weight(1f)) { 
                handleAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) 
            }
        }
        // Hàng 5: 1, 2, 3, +, =
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(symbol = "1", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(1)) }
            CalculatorButton(symbol = "2", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(2)) }
            CalculatorButton(symbol = "3", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(3)) }
            CalculatorButton(symbol = "+", modifier = Modifier.aspectRatio(1f).weight(1f)) { 
                handleAction(CalculatorAction.Operation(CalculatorOperation.Add)) 
            }
            CalculatorButton(symbol = "=", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Calculate) }
        }
        // Hàng 6: [ICON], 0, ., (Trống), (Trống)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            CalculatorButton(icon = Icons.Default.Star, modifier = Modifier.aspectRatio(1f).weight(1f), contentDescription = "Expand") { handleExpand() }
            CalculatorButton(symbol = "0", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Number(0)) }
            CalculatorButton(symbol = ".", modifier = Modifier.aspectRatio(1f).weight(1f)) { handleAction(CalculatorAction.Decimal) }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}
