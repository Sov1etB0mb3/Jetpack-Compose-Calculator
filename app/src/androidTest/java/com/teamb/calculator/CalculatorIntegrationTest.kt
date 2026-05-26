package com.teamb.calculator

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class CalculatorIntegrationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun basicAddition() {
        composeTestRule.onNodeWithText("7").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("10")
    }

    @Test
    fun pemdas() {
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("*").performClick()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("-").performClick()
        composeTestRule.onNodeWithText("6").performClick()
        composeTestRule.onNodeWithText("/").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("9")
    }

    @Test
    fun clear() {
        composeTestRule.onNodeWithText("7").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("AC").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("")
    }

    @Test
    fun decimal() {
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText(".").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("5.5")
    }

    @Test
    fun delete() {
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("Del").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("1")
    }

    @Test
    fun chainingAfterResult() {
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("12")
    }

    @Test
    fun factorial() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("!").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("120")
    }

    @Test
    fun square() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("x²").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("25")
    }

    @Test
    fun pi() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("π").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("3.141592653")
    }

    @Test
    fun sin() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("sin").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("0.5")
    }

    @Test
    fun sqrt() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("√").performClick()
        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("3")
    }

    @Test
    fun cos() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("cos").performClick()
        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("0")
    }

    @Test
    fun tan() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("tan").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("1")
    }

    @Test
    fun factorialInExpression() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("!").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("27")
    }

    @Test
    fun switchToStandardAfterAdvanced() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("π").performClick()
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithTag("Result").assertTextEquals("3")
    }
}
