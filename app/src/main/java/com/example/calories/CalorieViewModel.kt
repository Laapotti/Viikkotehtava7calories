package com.example.calories

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class CalorieViewModel : ViewModel() {
    private val _weightInput = mutableStateOf("")
    val weightInput: State<String> = _weightInput

    private val _heightInput = mutableStateOf("")
    val heightInput: State<String> = _heightInput

    private val _bmiResult = mutableStateOf(0.0)
    val bmiResult: State<Double> = _bmiResult

    fun onWeightInputChanged(newWeight: String) {
        _weightInput.value = newWeight
        calculateBmi()  // Calculate BMI whenever weight changes
    }

    fun onHeightInputChanged(newHeight: String) {
        _heightInput.value = newHeight
        calculateBmi()  // Calculate BMI whenever height changes
    }

    private fun calculateBmi() {
        val weight = _weightInput.value.toDoubleOrNull() ?: return
        val height = _heightInput.value.toDoubleOrNull() ?: return
        if (height > 0) {
            _bmiResult.value = weight / (height * height)
        } else {
            _bmiResult.value = 0.0 // Reset BMI if height is not valid
        }
    }
}
