package com.laroy.adscientiamtest.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.laroy.adscientiamtest.presentation.theme.AdScientiamTestTheme
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val appTheme = viewModel.appTheme.collectAsState().value

            AdScientiamTestTheme(appTheme) {
                MainScreen()
            }
        }
    }
}
