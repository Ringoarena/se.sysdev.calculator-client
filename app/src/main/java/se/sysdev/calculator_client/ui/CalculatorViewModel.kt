package se.sysdev.calculator_client.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
    var operandA by mutableStateOf("")
        private set
    var operandB by mutableStateOf("")
        private set

    fun updateOperandA(newValue: String) {
        operandA = newValue
    }

    fun updateOperandB(newValue: String) {
        operandB = newValue
    }

    fun calculateAddition() {
        val aNum = operandA.toInt()
        val bNum = operandB.toInt()
        val sum = aNum + bNum
        _uiState.value = CalculatorUiState(sum)
    }
}