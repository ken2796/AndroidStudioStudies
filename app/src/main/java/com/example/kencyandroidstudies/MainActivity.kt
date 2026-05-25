package com.example.kencyandroidstudies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kencyandroidstudies.cyan.ImageCyanViewPreview
import com.example.kencyandroidstudies.ken.ImageKenViewPreview
import com.example.kencyandroidstudies.ui.theme.KenCyAndroidStudiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainStudyProject()

        }
    }
}
@Composable
fun MainStudyProject() {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainKenView()
        MainCyanView()
    }
}
@Composable
fun MainKenView() {
    ImageKenViewPreview()
}

@Composable
fun MainCyanView(){
    ImageCyanViewPreview()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KenCyAndroidStudiesTheme {
        MainStudyProject()
    }
}