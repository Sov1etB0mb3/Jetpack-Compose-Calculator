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
            is CalculatorAction.Number -> performNumber(action.number)
            is CalculatorAction.Operation -> performOperation(action.operation)
        }
    }

    private fun performNumber(number: Int) {
        if (state.hasResult) state = CalculatorState()
        state = state.copy(currentNumber = state.currentNumber.plus(number.toString()))
    }

    private fun performOperation(op: CalculatorOperation) {
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

    private fun evaluate(expr: String): Double? = try {
        evalPostfix(infixToPostfix(tokenize(expr)))
    } catch (_: Exception) {
        null
    }

    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0
        while (i < expr.length) {
            val c = expr[i]
            when {
                c.isDigit() || c == '.' -> {
                    val start = i
                    while (i < expr.length && (expr[i].isDigit() || expr[i] == '.')) i++
                    tokens.add(expr.substring(start, i))
                }
                else -> {
                    tokens.add(c.toString())
                    i++
                }
            }
        }
        return tokens
    }

    private fun precedence(op: String): Int = when (op) {
        "+", "-" -> 1
        "*", "/" -> 2
        else -> 0
    }

    private fun infixToPostfix(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val ops = mutableListOf<String>()
        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)
                token == "(" -> ops.add(token)
                token == ")" -> {
                    while (ops.isNotEmpty() && ops.last() != "(")
                        output.add(ops.removeLast())
                    if (ops.isNotEmpty()) ops.removeLast()
                }
                else -> {
                    while (ops.isNotEmpty() && ops.last() != "("
                        && precedence(ops.last()) >= precedence(token))
                        output.add(ops.removeLast())
                    ops.add(token)
                }
            }
        }
        while (ops.isNotEmpty()) output.add(ops.removeLast())
        return output
    }

    private fun evalPostfix(postfix: List<String>): Double {
        val stack = mutableListOf<Double>()
        for (token in postfix) {
            val num = token.toDoubleOrNull()
            if (num != null) {
                stack.add(num)
            } else {
                val right = stack.removeLast()
                val left = stack.removeLast()
                stack.add(when (token) {
                    "+" -> left + right
                    "-" -> left - right
                    "*" -> left * right
                    "/" -> left / right
                    else -> throw IllegalArgumentException()
                })
            }
        }
        return stack.single()
    }
}
