package com.example.kencyandroidstudies.cyan

import com.example.kencyandroidstudies.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageCyanView(imageAlpha: Float){
    val cyanLogo = R.drawable.cyan_logo
    Image(
        painter = painterResource(id = cyanLogo),
        contentDescription = "Cyan's Logo",
        alpha = imageAlpha
    )
}

@Composable
fun ShowLogoButton(onClick: () -> Unit, textButton: String) {
    ElevatedButton(onClick = { onClick() },
        Modifier.border(1.dp, Color.Gray, RoundedCornerShape(50))) {
        Text(text = textButton)
    }
}

@Composable
fun ImageCyanViewContainer() {
    var showHideImage by remember { mutableStateOf(false) }

    val showHideText = if (showHideImage) "Hide" else "Show"
    val imageOpacity = if (showHideImage) 1f else 0f

    ShowLogoButton(
        textButton = "$showHideText Cyan's Logo",
        onClick = { showHideImage = !showHideImage }
    )

    ImageCyanView(imageAlpha = imageOpacity)
}

@Preview(showBackground = true)
@Composable
fun ImageCyanViewPreview() {
    ImageCyanViewContainer()
}
