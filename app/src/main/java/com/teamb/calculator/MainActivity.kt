package com.teamb.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamb.calculator.components.Calculator

class MainActivity : ComponentActivity() {

    private val viewModel : CalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewModel.state
            // Đã chuyển CalculatorTheme và Surface vào bên trong component Calculator 
            // để quản lý Dark Mode tập trung, tránh lỗi lệch màu nền (khung đen).
            Calculator(
                state = state, 
                modifier = Modifier.fillMaxSize(),
                onAction = viewModel::onAction,
                buttonSpacing = 12.dp
            )
        }
    }
}
