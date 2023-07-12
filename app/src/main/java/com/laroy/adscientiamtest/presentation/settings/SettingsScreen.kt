package com.laroy.adscientiamtest.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laroy.adscientiamtest.R
import com.laroy.adscientiamtest.presentation.position.SettingsEvent
import com.laroy.adscientiamtest.utils.UiText


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    SettingsScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SettingsScreenContent(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    expanded = true
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = state.appTheme?.asString() ?: ""
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            content = { Text(stringResource(R.string.app_theme_dark)) },
            onClick = {
                onEvent(SettingsEvent.OnAppThemeDarkClicked)
                expanded = false
            }
        )
        DropdownMenuItem(
            content = { Text(stringResource(R.string.app_theme_light)) },
            onClick = {
                onEvent(SettingsEvent.OnAppThemeLightClicked)
                expanded = false
            }
        )
        DropdownMenuItem(
            content = { Text(stringResource(R.string.app_theme_system)) },
            onClick = {
                onEvent(SettingsEvent.OnAppThemeSystemClicked)
                expanded = false
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenContentPreview() {
    SettingsScreenContent(
        state = SettingsState(
            appTheme = UiText.StringResource(R.string.app_theme_light)
        ),
        onEvent = {}
    )
}