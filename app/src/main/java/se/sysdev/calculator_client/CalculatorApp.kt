package se.sysdev.calculator_client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp() {
  Scaffold(
    topBar = { TopAppBar(title = { Text("Calculator") }) }
  ) { paddingValues ->
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      Column {
        Text(text = "content")
        Button(onClick = { CalculatorServiceFacade().calculateAddition(2,1) }) {
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
