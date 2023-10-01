package se.sysdev.calculator_client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import se.sysdev.calculator_client.ui.CalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp() {
  Scaffold(topBar = { TopAppBar(title = { Text("Calculator") }) }) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
    ) {
      val calculatorViewModel: CalculatorViewModel = viewModel()
      val calculatorUiState by calculatorViewModel.uiState.collectAsState()
      Column {
        Text(text = "${calculatorUiState.calculatedValue}")
        OutlinedTextField(value = "asd", onValueChange = InputHandler()::handleValueChanged )
        Button(onClick = {
          calculatorViewModel.calculateAddition(2,3)
//          CalculatorServiceFacade().calculateAddition(2, 1)
        }) {
          Text(text = "Calculate")
        }
      }
    }
  }
}
class InputHandler {
  fun handleValueChanged(input: String) {
    println(input)
  }
}

fun handleValueChange(input: String) {
  println(input)
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
  CalculatorApp()
}
