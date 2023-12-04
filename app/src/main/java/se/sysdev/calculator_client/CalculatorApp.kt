package se.sysdev.calculator_client

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import se.sysdev.calculator_client.ui.CalculatorViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp() {
  var expanded by remember { mutableStateOf(false) }
  val icon = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp
  var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

  Scaffold(topBar = { TopAppBar(title = { Text("Calculator") }) }) { it ->
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
    ) {
      val calculatorViewModel: CalculatorViewModel = viewModel()
      val calculatorUiState by calculatorViewModel.uiState.collectAsState()
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Text(text = "${calculatorUiState.calculatedValue}")
        OutlinedTextField(
          value = calculatorViewModel.operandA,
          onValueChange = { calculatorViewModel.updateOperandA(it) },
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword)
        )
        OutlinedTextField(
          value = calculatorViewModel.operandB,
          onValueChange = { calculatorViewModel.updateOperandB(it) },
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword)
        )
        Column {
          OutlinedTextField(
            value = calculatorViewModel.operation,
            onValueChange = { },
            modifier = Modifier
              .fillMaxWidth()
              .onGloballyPositioned { mTextFieldSize = it.size.toSize() },
            label = { Text("Select operation") },
            trailingIcon = {
              Icon(
                icon,
                "description",
                Modifier.clickable { expanded = !expanded })
            }
          )
          DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
              .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
          ) {
            calculatorUiState.availableOperations.forEach {
              DropdownMenuItem(
                text = { Text(text = it) },
                onClick = {
                  calculatorViewModel.updateOperation(it)
                  expanded = false
                })
            }
          }
        }
        Button(
          onClick = { calculatorViewModel.calculateAddition() }
        ) {
          Text(text = "Calculate")
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
  CalculatorApp()
}
