package se.sysdev.calculator_client.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import se.sysdev.calculator_client.network.CalculationRequest
import se.sysdev.calculator_client.network.CalculationResponse
import se.sysdev.calculator_client.network.CalculatorApi

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
    var operation by mutableStateOf("addition")
        private set
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
        val operation = operation
        val aNum: Long = operandA.toLong()
        val bNum: Long = operandB.toLong()
        val operands = listOf(aNum, bNum)
        val calculationRequest = CalculationRequest(operation, operands)
        viewModelScope.launch {
            val resp: Response<CalculationResponse> = try {
                CalculatorApi.retrofitService.calculate(calculationRequest)
            } catch (e: Exception) {
                println("Exception when calling server: ${e.message}")
                return@launch
            }
            if (resp.isSuccessful && resp.body() != null) {
                val resultValue = resp.body()!!.result
                println("Successfully received result from server: $resultValue")
                _uiState.value = CalculatorUiState(resultValue)
            }
        }
    }
}