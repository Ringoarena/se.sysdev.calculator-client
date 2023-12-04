package se.sysdev.calculator_client.ui

data class CalculatorUiState(
  val calculatedValue: Long = 0,
  val availableOperations: List<String> = emptyList()
)
