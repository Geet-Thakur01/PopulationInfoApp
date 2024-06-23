package com.example.populationinfoapp.presentation.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShowSnackBar(
    msg: String,
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    // LaunchedEffect to show Snackbar on app launch
    LaunchedEffect(Unit) {
        scope.launch {
            snackBarHostState.showSnackbar(
                message = msg,
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
        }
    }
}