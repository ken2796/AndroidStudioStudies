package com.example.kencyandroidstudies.cyan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.alpha
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun ShowBmiButton (onClick: () -> Unit, textButton: String){
    Button(onClick = {onClick() }) {
        Text(text= textButton)
    }
}

@Composable
fun BmiCyanView(){
    var showHideBmi by remember { mutableStateOf(false)}
    val showHideBmiText = if (showHideBmi) "Hide" else "Show"
    val showHideBmiFloat = if (showHideBmi) 1f else 0f


    ShowBmiButton(onClick = { showHideBmi = !showHideBmi },
    textButton = "$showHideBmiText BMI Calculator" )

    BmiTextFieldsAndButton(genAlpha = showHideBmiFloat)

//    if (showHideBmi){
//        Row (
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            BmiTextFieldsAndButton()
//        }
//    }


}

@Composable
fun BmiTextFieldsAndButton(modifier: Modifier = Modifier, genAlpha: Float){
    var age by remember { mutableStateOf("") }
    var heightInFoot by remember { mutableStateOf("") }
    var heightInInch by remember { mutableStateOf("") }
    var weightInLbs by remember { mutableStateOf("") }

    var bmiResultText by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
            .alpha(genAlpha),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                singleLine = true,
                label = { Text(text = "Enter your Age")}
            )
            SubmitAgeButton {  }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = heightInFoot,
                onValueChange = { heightInFoot = it },
                singleLine = true,
                label = { Text("Enter your height in Foot")}
            )
            SubmitHeightInFootButton {  }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = heightInInch,
                onValueChange = { heightInInch = it },
                singleLine = true,
                label = { Text("Enter your height in Inch")}
            )
            SubmitHeightInInchButton {  }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = weightInLbs,
                onValueChange = { weightInLbs = it },
                singleLine = true,
                label = { Text("Enter your weight in LBS") }
            )
            SubmitWeightLBSButton { }
        }
        ComputeBmiButton { bmiResultText = calculateBmi(heightInFoot, heightInInch, weightInLbs)}
        Text(text = bmiResultText.toString())


    }


}

fun calculateBmi(footToIntInput: String, inchToInt: String, weightToInt: String): Int {
    val footToInt = footToIntInput.toFloatOrNull() ?: 0f
    val inchToInt = inchToInt.toFloatOrNull() ?: 0f
    val weightToInt = weightToInt.toFloatOrNull() ?: 0f

    val totalHeight = (footToInt*12f)+inchToInt
    val totalHeightSquared = totalHeight  * totalHeight

    val bmi = (703f*weightToInt)/totalHeightSquared
    return bmi.roundToInt()
}

@Composable
fun SubmitAgeButton(onClick: () -> Unit){
    FilledTonalButton(onClick = {onClick()}) {
        Text("Submit Age")
    }
}

@Composable
fun SubmitHeightInFootButton(onClick: () -> Unit){
    OutlinedButton (onClick = {onClick()}) {
        Text("Submit Foot")
    }
}

@Composable
fun SubmitHeightInInchButton(onClick: () -> Unit){
    ElevatedButton(onClick = {onClick()}) {
        Text("Submit Inch")
    }
}

@Composable
fun SubmitWeightLBSButton(onClick: () -> Unit){
    TextButton(onClick = {onClick()}) {
        Text("Submit KG")
    }
}

@Composable
fun ComputeBmiButton(onClick: () -> Unit){
    FilledTonalButton(onClick = {onClick()}) {
        Text("Compute BMI")
    }
}

@Preview(showBackground = true)
@Composable
fun BmiCyanViewPreview() {
    BmiCyanView()
}