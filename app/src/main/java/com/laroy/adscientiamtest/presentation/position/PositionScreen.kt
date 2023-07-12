package com.laroy.adscientiamtest.presentation.position

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laroy.adscientiamtest.R
import com.laroy.adscientiamtest.domain.model.Position
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PositionsScreen(viewModel: PositionViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    PositionsScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun PositionsScreenContent(
    state: PositionState,
    onEvent: (PositionEvent) -> Unit
) {
    var showClearDialog by remember { mutableStateOf(false) }

    val timeFormatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    val dateFormatter: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

    Box(modifier = Modifier.fillMaxSize()){
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
            )
        } else {
            val lazyListSate = rememberLazyListState()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = lazyListSate,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                items(
                    items = state.list
                ) { position ->
                    Item(position, timeFormatter, dateFormatter)
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SmallFloatingActionButton(
                onClick = {
                    showClearDialog = showClearDialog.not()
                },
                containerColor = colors.secondaryVariant,
                shape = RoundedCornerShape(12.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_clear_all),
                    contentDescription = "Clear",
                    tint = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(R.string.inverse_order),
                        color = Color.White
                    )
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_sort),
                        contentDescription = "Navigate FAB",
                        tint = Color.White,
                    )
                },
                onClick = {
                    onEvent(PositionEvent.OnInverseOrderClicked)
                }
            )
        }
    }

    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = {
                Text(stringResource(R.string.clear_all_positions))
            },
            text = {
                Text(stringResource(R.string.can_not_be_undone))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showClearDialog = false
                        onEvent(PositionEvent.OnClearClicked)
                    }
                ) {
                    Text(stringResource(R.string.clear_all).uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearDialog = false }) {
                    Text(stringResource(android.R.string.cancel).uppercase())
                }
            },
        )
    }
}

@Composable
fun Item(position: Position, timeFormatter: SimpleDateFormat, dateFormatter: DateFormat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = "${dateFormatter.format(position.date)} ${timeFormatter.format(position.date)}" ,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = "{${position.x} ; ${position.y}}",
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PositionsScreenPreview() {
    PositionsScreenContent(
        state = PositionState(
            isLoading = false,
            list = mutableListOf(
                Position(100, 10, 10),
                Position(200, 20, 20),
                Position(300, 30, 30)
            )
        ),
        onEvent = {}
    )
}
