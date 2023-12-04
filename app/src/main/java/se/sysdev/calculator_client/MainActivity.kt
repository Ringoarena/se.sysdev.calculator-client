package se.sysdev.calculator_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import se.sysdev.calculator_client.ui.CalculatorViewModel
import se.sysdev.calculator_client.ui.theme.CalculatorclientTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val calculatorViewModel: CalculatorViewModel = viewModel()
      calculatorViewModel.getOperations()
      CalculatorclientTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          CalculatorApp()
        }
      }
    }
  }
}
