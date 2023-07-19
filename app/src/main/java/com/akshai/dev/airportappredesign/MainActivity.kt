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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.akshai.dev.airportappredesign.ui.MViewModel
import com.akshai.dev.airportappredesign.ui.components.CheckInOutCompose
import com.akshai.dev.airportappredesign.ui.components.CheckInOutSelection
import com.akshai.dev.airportappredesign.ui.components.UIEvent
import com.akshai.dev.airportappredesign.ui.theme.AirportAppRedesignTheme
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    private val viewModel: MViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportAppRedesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Check In/Out",
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )

                        var isClicked by remember { mutableStateOf(false) }

                        CheckInOutCompose(
                            onClick = { isClicked = !isClicked },
                            click = isClicked,
                        )

                        Button(onClick = {

                            var dt = Date()
                            val c: Calendar = Calendar.getInstance()
                            c.time = dt
                            c.add(Calendar.DATE, 1)
                            dt = c.time

                            when (viewModel.checkState.value.selection) {
                                CheckInOutSelection.In -> viewModel.onEvent(
                                    UIEvent.CheckInChanged(
                                        dt.toString()
                                    )
                                )

                                CheckInOutSelection.Out -> viewModel.onEvent(
                                    UIEvent.CheckOutChanged(
                                        dt.toString()
                                    )
                                )
                            }


                        }) {
                            Text(text = "Add Date")
                        }

                    }


                }

                /* Text(text = "Date Picker", Modifier.padding(top = 16.dp, bottom = 8.dp))

 val days = List(30, { it * 1 })
 WheelPicker(count = 30, rowCount = 5) {
     Text(text = "${days[it]}")
 }
*/

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