package com.teamb.calculator

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorIntegrationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun assertDisplay(expected: String) {
        composeTestRule.onNode(
            hasText(expected, substring = false),
            useUnmergedTree = true
        ).assertExists()
    }

    @Test
    fun basicAddition() {
        composeTestRule.onNodeWithText("7").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("10.0")
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
        assertDisplay("9.0")
    }

    @Test
    fun decimal() {
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText(".").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("5.5")
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
        assertDisplay("12.0")
    }

    @Test
    fun factorial() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("!").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("120.0")
    }

    @Test
    fun square() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("x²").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("25.0")
    }

    @Test
    fun pi() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("π").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("3.14159265")
    }

    @Test
    fun sin() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("sin").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("0.5")
    }
    @Test
    fun cos() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("cos").performClick()
        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
//        composeTestRule.onNodeWithTag("Result").assertTextEquals("0.0")
        assertDisplay("0.0")
    }

    @Test
    fun tan() {
        composeTestRule.onNodeWithContentDescription("Expand").performClick()
        composeTestRule.onNodeWithText("tan").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("1.0")
    }
    @Test
    fun sqrt() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("√").performClick()
        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText(")").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("3.0")
    }

    @Test
    fun factorialInExpression() {
        composeTestRule.onNodeWithContentDescription("Expand", substring = true).performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("4").performClick()
        composeTestRule.onNodeWithText("!").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        assertDisplay("27.0")
    }
}
