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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akshai.dev.airportappredesign.ui.MViewModel


@Composable
fun CheckInOutCompose(
    size: Dp = 100.dp,
    padding: Dp = 6.dp,
    toggleShape: Shape = CircleShape,
    click: Boolean = false,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 500),
    onClick: () -> Unit,
    mViewModel: MViewModel = viewModel(),
) {


    val checkData = mViewModel.checkState.value

    val offset by animateDpAsState(
        targetValue = if (!click) 0.dp else size * 2, animationSpec = animationSpec
    )

    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .width(size * 4)
            .height(size)
            .clip(shape = toggleShape)
            .background(color = Color(0xFFDADADA)),
    ) {
        Box(
            modifier = Modifier
                .width(size * 2)
                .height(size)
                .offset(x = offset)
                .padding(all = padding)
                .shadow(12.dp, shape = toggleShape)
                .background(color = Color(0xFFFFFFFF))


        ) {}

        Row() {
            Column(
                modifier = Modifier
                    .width(size * 2)
                    .height(size)
                    .padding(start = size / 3)
                    .clickable {
                        if (checkData.selection.name == "Out") {
                            checkData.selection = CheckInOutSelection.In
                            onClick()
                        }
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(text = "Check-in")
                Text(text = checkData.checkInDate)
            }


            Column(
                modifier = Modifier
                    .width(size * 2)
                    .height(size)
                    .padding(start = size / 3)
                    .clickable {
                        if (checkData.selection.name == "In") {
                            checkData.selection = CheckInOutSelection.Out
                            onClick()
                        }
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(text = "Check-out")
                Text(text = checkData.checkOutDate)

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

