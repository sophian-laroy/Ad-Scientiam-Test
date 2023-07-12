package com.laroy.adscientiamtest.presentation.drag

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.laroy.adscientiamtest.presentation.theme.Yellow_AdScientiamTest
import kotlin.math.roundToInt

const val DEFAULT_SIZE_IN_PX = 70f

@Composable
fun DragScreen(
    viewModel: DragViewModel = hiltViewModel()
) {
    DragScreenContent(
        onEvent = viewModel::onEvent
    )
}

@Composable
fun DragScreenContent(
    onEvent: (DragEvent) -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {
                onEvent(DragEvent.OnScreenPaused)
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }
    ) {
        DraggableComponent(
            content = {
                ExampleBox(
                    shape = RectangleShape,
                    sizeInPx = DEFAULT_SIZE_IN_PX,
                    color = Yellow_AdScientiamTest
                )
            },
            {
                size
            }
        ) { x, y ->
            Log.d("Coordinates", "PositionChanged ${x.toInt()} ${y.toInt()}")
            onEvent(DragEvent.PositionChanged(x.toInt(), y.toInt()))
        }
    }
}

@Composable
fun ExampleBox(
    shape: Shape,
    sizeInPx: Float,
    color: Color
) {
    Box(
        modifier = Modifier
            .size(sizeInPx.pxToDp())
            .clip(shape)
            .background(color)
    )
}

@Composable
fun DraggableComponent(
    content: @Composable () -> Unit,
    getParentSize: () -> IntSize,
    onPositionChanged: (Float, Float) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var centerX by remember { mutableStateOf(0f) }
    var centerY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(
        content = {
            content()
        },
        modifier =
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX =
                        (offsetX + dragAmount.x).coerceIn(
                            0f,
                            getParentSize().width.toFloat() - size.width
                        )
                    offsetY = (offsetY + dragAmount.y).coerceIn(
                        0f,
                        getParentSize().height.toFloat() - size.height
                    )
                }
            }
            .onSizeChanged {
                size = it
            }
            .onGloballyPositioned { coordinates ->
                val position = coordinates.positionInRoot()
                centerX = position.x + (size.width) / 2
                centerY = position.y + (size.height) / 2

                onPositionChanged(centerX, centerY)
            }
    )
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Float.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DragScreenContentPreview() {
    DragScreenContent(
        onEvent = {}
    )
}
