package com.akshai.dev.airportappredesign.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Test(){
    Column(modifier = Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier
            .size(25.dp)
            .background(Color.Red)) {

        }
        Box(modifier = Modifier
            .size(25.dp)
            .background(Color.Green)) {

        }
    }
}

@Preview
@Composable
fun ShowPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),) {
        Test()
    }

}