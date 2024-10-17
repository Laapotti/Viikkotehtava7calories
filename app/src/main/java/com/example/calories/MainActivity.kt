package com.example.calories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calories.ui.theme.CaloriesTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BmiScreen()
                }
            }
        }
    }
}

@Composable
fun BmiScreen(viewModel: CalorieViewModel = viewModel()) {
    val weightInput by viewModel.weightInput
    val heightInput by viewModel.heightInput
    val bmiResult by viewModel.bmiResult

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.bmi_calculator),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        WeightField(weightInput = weightInput, onValueChange = viewModel::onWeightInputChanged)

        HeightField(heightInput = heightInput, onValueChange = viewModel::onHeightInputChanged)

        Text(
            text = "Your BMI: %.2f".format(bmiResult),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun HeightField(heightInput: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = heightInput,
        onValueChange = onValueChange,
        label = { Text(text = "height") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun WeightField(weightInput: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = weightInput,
        onValueChange = onValueChange,
        label = { Text(text = "weight") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CaloriesTheme {
        BmiScreen()
    }
}
