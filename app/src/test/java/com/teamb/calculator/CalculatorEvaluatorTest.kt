package com.teamb.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CalculatorEvaluatorTest {

    private fun assertEval(expected: Double, expr: String, delta: Double = 1e-10) {
        assertEquals(expected, CalculatorEvaluator.evaluate(expr)!!, delta)
    }

    @Test
    fun addition() {
        assertEval(5.0, "2+3")
    }

    @Test
    fun subtraction() {
        assertEval(1.0, "3-2")
    }

    @Test
    fun multiplication() {
        assertEval(6.0, "2*3")
    }

    @Test
    fun division() {
        assertEval(2.0, "6/3")
    }

    @Test
    fun pemdas() {
        assertEval(9.0, "3+4*2-6/3")
    }

    @Test
    fun parentheses() {
        assertEval(14.0, "(3+4)*2")
    }

    @Test
    fun nestedParentheses() {
        assertEval(18.0, "((3+4)*2)+4")
    }

    @Test
    fun decimal() {
        assertEval(5.5, "2.5+3")
    }

    @Test
    fun divisionByZeroReturnsInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, CalculatorEvaluator.evaluate("1/0")!!, 0.0)
    }

    @Test
    fun factorial() {
        assertEval(120.0, "5!")
    }

    @Test
    fun factorialOfZero() {
        assertEval(1.0, "0!")
    }

    @Test
    fun factorialNegativeReturnsNull() {
        assertNull(CalculatorEvaluator.evaluate("-3!"))
    }

    @Test
    fun factorialTooLargeReturnsNull() {
        assertNull(CalculatorEvaluator.evaluate("21!"))
    }

    @Test
    fun factorialInExpression() {
        assertEval(27.0, "3+4!")
    }

    @Test
    fun pi() {
        assertEval(Math.PI, "π")
    }

    @Test
    fun piInExpression() {
        assertEval(Math.PI + 1, "π+1")
    }

    @Test
    fun square() {
        assertEval(25.0, "5²")
    }

    @Test
    fun squareOfZero() {
        assertEval(0.0, "0²")
    }

    @Test
    fun squareInExpression() {
        assertEval(29.0, "4+5²")
    }

    @Test
    fun sin() {
        assertEval(0.5, "sin(30)")
    }

    @Test
    fun sinOfZero() {
        assertEval(0.0, "sin(0)")
    }

    @Test
    fun cos() {
        assertEval(0.0, "cos(90)")
    }

    @Test
    fun cosOfZero() {
        assertEval(1.0, "cos(0)")
    }

    @Test
    fun tan() {
        assertEval(1.0, "tan(45)")
    }

    @Test
    fun tanOfZero() {
        assertEval(0.0, "tan(0)")
    }

    @Test
    fun sqrt() {
        assertEval(3.0, "sqrt(9)")
    }

    @Test
    fun sqrtOfZero() {
        assertEval(0.0, "sqrt(0)")
    }

    @Test
    fun sqrtInExpression() {
        assertEval(7.0, "4+sqrt(9)")
    }

    @Test
    fun sinInExpression() {
        assertEval(3.5, "3+sin(30)")
    }

    @Test
    fun combinedOperators() {
        assertEval(131.0, "4+5!+sqrt(9)+2²")
    }

    @Test
    fun factorialPemdas() {
        assertEval(74.0, "2+3*4!")
    }

    @Test
    fun malformedExpression() {
        assertNull(CalculatorEvaluator.evaluate("++"))
    }

    @Test
    fun emptyExpression() {
        assertNull(CalculatorEvaluator.evaluate(""))
    }

    @Test
    fun tokenizeSimple() {
        assertEquals(listOf("3", "+", "4"), CalculatorEvaluator.tokenize("3+4"))
    }

    @Test
    fun tokenizeWithParens() {
        assertEquals(listOf("(", "3", "+", "4", ")"), CalculatorEvaluator.tokenize("(3+4)"))
    }

    @Test
    fun tokenizeWithFunction() {
        assertEquals(listOf("sin", "(", "30", ")"), CalculatorEvaluator.tokenize("sin(30)"))
    }

    @Test
    fun tokenizeWithPi() {
        assertEquals(listOf("π"), CalculatorEvaluator.tokenize("π"))
    }

    @Test
    fun tokenizeWithSquare() {
        assertEquals(listOf("5", "²"), CalculatorEvaluator.tokenize("5²"))
    }

    @Test
    fun tokenizeWithFactorial() {
        assertEquals(listOf("5", "!"), CalculatorEvaluator.tokenize("5!"))
    }

    @Test
    fun tokenizeDecimal() {
        assertEquals(listOf("3.5"), CalculatorEvaluator.tokenize("3.5"))
    }

    @Test
    fun tokenizeComplex() {
        assertEquals(
            listOf("4", "+", "5", "!", "+", "sqrt", "(", "9", ")", "+", "2", "²"),
            CalculatorEvaluator.tokenize("4+5!+sqrt(9)+2²")
        )
    }
}
