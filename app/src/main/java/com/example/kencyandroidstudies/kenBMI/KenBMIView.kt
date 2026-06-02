package com.example.kencyandroidstudies.kenBMI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class BmiField { AGE, GENDER, HEIGHT, WEIGHT }

@Composable
fun KenBMIView() {
    var ageInput by remember { mutableStateOf("") }
    var genderInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    var savedAge by remember { mutableStateOf("") }
    var savedGender by remember { mutableStateOf("") }
    var savedHeight by remember { mutableStateOf("") }
    var savedWeight by remember { mutableStateOf("") }

    var bmiResult by remember { mutableStateOf("") }

    val canCalculate = savedHeight.isNotBlank() && savedWeight.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Ken BMI Calculator",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        BmiFieldRow(
            field = BmiField.AGE,
            label = "Age",
            value = ageInput,
            onValueChange = { ageInput = it },
            onSave = { savedAge = ageInput }
        )
        BmiFieldRow(
            field = BmiField.GENDER,
            label = "Gender",
            value = genderInput,
            onValueChange = { genderInput = it },
            onSave = { savedGender = genderInput }
        )
        BmiFieldRow(
            field = BmiField.HEIGHT,
            label = "Height (cm)",
            value = heightInput,
            onValueChange = { heightInput = it },
            onSave = { savedHeight = heightInput }
        )
        BmiFieldRow(
            field = BmiField.WEIGHT,
            label = "Weight (kg)",
            value = weightInput,
            onValueChange = { weightInput = it },
            onSave = { savedWeight = weightInput }
        )

        if (canCalculate) {
            TextButton(onClick = { bmiResult = calculateBmi(savedHeight, savedWeight) }) {
                Text(text = "Calculate BMI")
            }
        }

        if (bmiResult.isNotBlank()) {
            Text(
                text = bmiResult,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun BmiFieldRow(
    field: BmiField,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.weight(1f)
        )

        when (field) {
            BmiField.AGE -> Button(onClick = onSave) { Text("Save") }
            BmiField.GENDER -> FilledTonalButton(onClick = onSave) { Text("Save") }
            BmiField.HEIGHT -> ElevatedButton(onClick = onSave) { Text("Save") }
            BmiField.WEIGHT -> OutlinedButton(onClick = onSave) { Text("Save") }
        }
    }
}

private fun calculateBmi(heightCm: String, weightKg: String): String {
    val height = heightCm.toDoubleOrNull()
    val weight = weightKg.toDoubleOrNull()
    if (height == null || weight == null || height <= 0.0) {
        return "Invalid height or weight"
    }
    val heightM = height / 100.0
    val bmi = weight / (heightM * heightM)
    val category = when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal"
        bmi < 30.0 -> "Overweight"
        else -> "Obese"
    }
    return "BMI: %.1f (%s)".format(bmi, category)
}

@Preview(showBackground = true)
@Composable
fun KenBMIViewPreview() {
    KenBMIView()
}
