package com.skmdroid.moviesbiz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.skmdroid.moviesbiz.presentation.ui.MainScreen
import com.skmdroid.moviesbiz.ui.theme.MoviesBizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesBizTheme {
                MainNavigation()
            }
        }
    }
}