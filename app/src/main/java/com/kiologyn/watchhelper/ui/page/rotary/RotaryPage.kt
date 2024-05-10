package com.kiologyn.watchhelper.ui.page.rotary

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Icon
import com.kiologyn.watchhelper.component.WebSocketConnection
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun RotaryPage(viewModel: RotaryPageViewModel = viewModel()) {
    val focusRequester: FocusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onRotaryScrollEvent { event ->
                viewModel.shiftValue(event.verticalScrollPixels)
                true
            }
            .focusRequester(focusRequester)
            .focusable()
        ,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radian = viewModel.value * Math.PI / 180
            val strokeWidth = 15f
            val center = size.width/2
            val radius = center - strokeWidth/2
            drawLine(
                start = Offset(center, center),
                end = Offset(
                    (center + radius * cos(radian)).toFloat(),
                    (center + radius * sin(radian)).toFloat(),
                ),

                color = Color.White,
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}