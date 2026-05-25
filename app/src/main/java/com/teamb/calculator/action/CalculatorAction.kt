package com.teamb.calculator.action

sealed class CalculatorAction {
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
    object Calculate : CalculatorAction()
    object OpenParen : CalculatorAction()
    object CloseParen : CalculatorAction()
    object Factorial : CalculatorAction()
    object Pi : CalculatorAction()
    object Sin : CalculatorAction()
    object Cos : CalculatorAction()
    object Tan : CalculatorAction()
    object Sqrt : CalculatorAction()
    object Square : CalculatorAction()
    data class Number(val number: Int) : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()
}
