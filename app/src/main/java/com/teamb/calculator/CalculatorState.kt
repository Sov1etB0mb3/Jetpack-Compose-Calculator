package com.teamb.calculator

data class CalculatorState(
    val expression: String = "",
    val currentNumber: String = "",
    val hasResult: Boolean = false
)
