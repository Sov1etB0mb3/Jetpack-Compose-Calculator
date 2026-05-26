package com.teamb.calculator

object CalculatorEvaluator {

    private val knownFunctions = setOf("sin", "cos", "tan", "sqrt")

    fun evaluate(expr: String): Double? = try {
        evalPostfix(infixToPostfix(tokenize(expr)))
    } catch (_: Exception) {
        null
    }

    fun tokenize(expr: String): List<String> {
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
                c.isLetter() -> {
                    val start = i
                    while (i < expr.length && expr[i].isLetter()) i++
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

    fun infixToPostfix(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val ops = mutableListOf<String>()
        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)
                token == "π" -> output.add(token)
                token == "(" -> ops.add(token)
                token in knownFunctions -> ops.add(token)
                token == "!" || token == "²" -> output.add(token)
                token == ")" -> {
                    while (ops.isNotEmpty() && ops.last() != "(")
                        output.add(ops.removeLast())
                    if (ops.isNotEmpty()) ops.removeLast()
                    if (ops.isNotEmpty() && ops.last() in knownFunctions)
                        output.add(ops.removeLast())
                }
                else -> {
                    while (ops.isNotEmpty() && ops.last() !in knownFunctions && ops.last() != "("
                        && precedence(ops.last()) >= precedence(token))
                        output.add(ops.removeLast())
                    ops.add(token)
                }
            }
        }
        while (ops.isNotEmpty()) output.add(ops.removeLast())
        return output
    }

    fun evalPostfix(postfix: List<String>): Double {
        val stack = mutableListOf<Double>()
        for (token in postfix) {
            when {
                token.toDoubleOrNull() != null -> stack.add(token.toDouble())
                token == "π" -> stack.add(Math.PI)
                token == "!" -> {
                    val n = stack.removeLast().toLong()
                    if (n < 0 || n > 20) throw IllegalArgumentException()
                    stack.add((1..n).fold(1L) { acc, i -> acc * i }.toDouble())
                }
                token == "²" -> {
                    val n = stack.removeLast()
                    stack.add(n * n)
                }
                token in knownFunctions -> {
                    val arg = stack.removeLast()
                    stack.add(when (token) {
                        "sin" -> Math.sin(Math.toRadians(arg))
                        "cos" -> Math.cos(Math.toRadians(arg))
                        "tan" -> Math.tan(Math.toRadians(arg))
                        "sqrt" -> Math.sqrt(arg)
                        else -> throw IllegalArgumentException()
                    })
                }
                else -> {
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
        }
        return stack.single()
    }
}
