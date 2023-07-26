package com.akshai.dev.airportappredesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akshai.dev.airportappredesign.ui.MViewModel
import com.akshai.dev.airportappredesign.ui.components.CheckInOut
import com.akshai.dev.airportappredesign.ui.components.MonthlyCalenderView
import com.akshai.dev.airportappredesign.ui.components.WheelPicker
import com.akshai.dev.airportappredesign.ui.theme.AirportAppRedesignTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportAppRedesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFAFAFA)
                ) {

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(20.dp)
                    ) {


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            elevation = CardDefaults.cardElevation(6.dp),
                            colors = CardDefaults.cardColors(Color(0xFFFFFFFF))
                        ) {


                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        top = 18.dp,
                                        bottom = 18.dp
                                    )

                            ) {
                                Text(
                                    text = "Where to?", style = MaterialTheme.typography.bodyLarge
                                )
                                Text(text = "(HPN) Westchester\nCounty Airport ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF008489),)
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 7.dp),
                            elevation = CardDefaults.cardElevation(6.dp),
                            colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
                        ) {
                            Column() {
                                Text(
                                    text = "When do you want to book?", style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(24.dp)
                                )
                            }
                            var isClicked by remember { mutableStateOf(false) }

                            CheckInOut(
                                onClick = { isClicked = !isClicked },
                                click = isClicked,
                            )

                            MonthlyCalenderView()
                            val minutes = listOf<String>("00","15","30","45",)
                            val hours = mutableListOf<Int>()
                            for (i in 0..12){
                                hours.add(i)
                            }
                            val apm = listOf<String>("AM","PM")
                           /* Row() {
                                WheelPicker(count = hours.size, rowCount =5 ) {
                                    Text(text = hours[it].toString())
                                }
                                WheelPicker(count = minutes.size, rowCount = 5) {
                                    Text(text = minutes[it])
                                }
                                WheelPicker(count = 2, rowCount = 2   ) {
                                    Text(text = apm[it])
                                }
                            }*/

                        }
                    }
                }
            }
        }
    }


}


@Composable
fun Switcher(
    modifier: Modifier = Modifier,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    parentShape: Shape = CircleShape,
    darkTheme: Boolean = false,
    padding: Dp = 10.dp,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {

    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec
    )


    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        // .clip(shape = parentShape)
        .clickable { onClick() }
        // .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}

        Row(
            modifier = Modifier.border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = parentShape
            )
        ) {

            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.primary
                )
            }

            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AirportAppRedesignTheme {
        var darkTheme by remember { mutableStateOf(false) }
        Switcher(
            darkTheme = darkTheme,
            onClick = { darkTheme = !darkTheme }
        )
    }
}