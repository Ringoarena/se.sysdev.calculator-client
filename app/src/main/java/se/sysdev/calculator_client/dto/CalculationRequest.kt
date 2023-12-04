package se.sysdev.calculator_client.dto

data class CalculationRequest(
  val operation: String,
  val operands: List<Long>
)