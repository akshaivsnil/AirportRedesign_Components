package com.akshai.dev.airportappredesign.ui.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akshai.dev.airportappredesign.ui.MViewModel


@Composable
fun CheckInOut(
    size: Dp = 80.dp,
    padding: Dp = 6.dp,
    innerPadding: Dp = 20.dp,
    toggleShape: Shape = CircleShape,
    click: Boolean = false,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 500),
    onClick: () -> Unit,
    mViewModel: MViewModel = viewModel(),
) {


    val checkData = mViewModel.checkState.value

    var cardSize by remember {
        mutableStateOf(0.dp)
    }

    val offset by animateDpAsState(
        targetValue = if (!click) 0.dp else (cardSize / 5) - innerPadding,
        animationSpec = animationSpec
    )


    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
            .height(size)
            .clip(shape = toggleShape)
            .background(color = Color(0xFFF2F2F2))
            .onGloballyPositioned { layoutCoordinates ->
                cardSize = layoutCoordinates.size.width.dp
            },
    ) {
        Box(
            modifier = Modifier
                .width(cardSize / 5)
                .height(size)
                .offset(x = offset)
                .padding(all = padding)
                .shadow(6.dp, shape = toggleShape)
                .background(color = Color(0xFFFFFFFF))


        ) {}


        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size)
                    .weight(1f)
                    .clickable {
                        println(checkData.selection.name)
                        if (checkData.selection.name == CheckInOutSelection.Out.name) {
                            checkData.selection = CheckInOutSelection.In
                            onClick()
                        }
                    }
                    //.background(Color.Red)
                    .padding(start = innerPadding),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = "Check-in",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF818181)
                )
                Text(text = checkData.checkInDate,style = MaterialTheme.typography.labelSmall,)
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size)
                    .weight(1f)
                    .clickable {
                        println(checkData.selection.name)
                        if (checkData.selection.name == CheckInOutSelection.In.name) {
                            checkData.selection = CheckInOutSelection.Out
                            onClick()
                        }
                    }
                    // .background(Color.Green)
                    .padding(start = innerPadding),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = "Check-out",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF818181)
                )
                Text(text = checkData.checkOutDate, style = MaterialTheme.typography.labelSmall,)

            }
        }


    }
}

data class CheckInOutData(
    val checkInDate: String = "",
    val checkOutDate: String = "",
    var selection: CheckInOutSelection = CheckInOutSelection.In,
)

enum class CheckInOutSelection {
    In, Out
}

sealed class UIEvent {
    data class CheckInChanged(val date: String) : UIEvent()
    data class CheckOutChanged(val date: String) : UIEvent()
    object Reset : UIEvent()
}

