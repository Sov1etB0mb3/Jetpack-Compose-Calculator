package com.teamb.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.teamb.calculator.action.CalculatorAction
import com.teamb.calculator.action.CalculatorOperation

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> performCalculate()
            CalculatorAction.Clear -> state = CalculatorState()
            CalculatorAction.Decimal -> performDecimal()
            CalculatorAction.Delete -> performDelete()
            CalculatorAction.OpenParen -> performOpenParen()
            CalculatorAction.CloseParen -> performCloseParen()
            CalculatorAction.Factorial -> performFactorial()
            CalculatorAction.Pi -> performPi()
            CalculatorAction.Sin -> performSin()
            CalculatorAction.Cos -> performCos()
            CalculatorAction.Tan -> performTan()
            CalculatorAction.Sqrt -> performSqrt()
            CalculatorAction.Square -> performSquare()
            is CalculatorAction.Number -> performNumber(action.number)
            is CalculatorAction.Operation -> performOperation(action.operation)
        }
    }

    private fun performNumber(number: Int) {
        if (state.hasResult) state = CalculatorState()
        state = state.copy(currentNumber = state.currentNumber.plus(number.toString()))
    }

    private fun performOperation(op: CalculatorOperation) {
        if (state.hasResult) {
            state = state.copy(
                expression = state.expression + op.operator,
                currentNumber = "",
                hasResult = false
            )
            return
        }
        val expr = state.expression + state.currentNumber
        val stripped = if (expr.endsWith("+") || expr.endsWith("-") ||
            expr.endsWith("*") || expr.endsWith("/")
        ) expr.dropLast(1) + op.operator else expr + op.operator
        state = state.copy(expression = stripped, currentNumber = "")
    }

    private fun performDecimal() {
        if (state.currentNumber.contains(".")) return
        state = state.copy(
            currentNumber = if (state.currentNumber.isEmpty()) "0."
            else state.currentNumber.plus(".")
        )
    }

    private fun performDelete() {
        when {
            state.currentNumber.isNotEmpty() ->
                state = state.copy(currentNumber = state.currentNumber.dropLast(1))
            state.expression.isNotEmpty() ->
                state = state.copy(expression = state.expression.dropLast(1))
        }
    }

    private fun performOpenParen() {
        state = state.copy(
            expression = state.expression + state.currentNumber + "(",
            currentNumber = ""
        )
    }

    private fun performCloseParen() {
        state = state.copy(
            expression = state.expression + state.currentNumber + ")",
            currentNumber = ""
        )
    }

    private fun performCalculate() {
        val expr = state.expression + state.currentNumber
        if (expr.isBlank()) return
        val evaluated = evaluate(expr)
        if (evaluated != null) {
            state = CalculatorState(
                expression = evaluated.toString().take(10),
                hasResult = true
            )
        }
    }

    private fun performFactorial() {
        if (state.currentNumber.isEmpty() && state.expression.isEmpty()) return
        state = state.copy(
            expression = state.expression + state.currentNumber + "!",
            currentNumber = ""
        )
    }

    private fun performPi() {
        state = state.copy(currentNumber = state.currentNumber + "π")
    }

    private fun performSin() {
        state = state.copy(
            expression = state.expression + state.currentNumber + "sin(",
            currentNumber = ""
        )
    }

    private fun performCos() {
        state = state.copy(
            expression = state.expression + state.currentNumber + "cos(",
            currentNumber = ""
        )
    }

    private fun performTan() {
        state = state.copy(
            expression = state.expression + state.currentNumber + "tan(",
            currentNumber = ""
        )
    }

    private fun performSqrt() {
        state = state.copy(
            expression = state.expression + state.currentNumber + "sqrt(",
            currentNumber = ""
        )
    }

    private fun performSquare() {
        if (state.currentNumber.isEmpty() && state.expression.isEmpty()) return
        state = state.copy(
            expression = state.expression + state.currentNumber + "²",
            currentNumber = ""
        )
    }

    private fun evaluate(expr: String): Double? = CalculatorEvaluator.evaluate(expr)
}
