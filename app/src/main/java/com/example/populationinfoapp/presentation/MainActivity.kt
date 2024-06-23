package com.example.populationinfoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.populationinfoapp.R
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import com.example.populationinfoapp.presentation.components.CountryListScreen
import com.example.populationinfoapp.presentation.components.HomeContent
import com.example.populationinfoapp.presentation.components.LoadingScreen
import com.example.populationinfoapp.presentation.components.ShowEmptyScreen
import com.example.populationinfoapp.presentation.components.ShowSnackBar
import com.example.populationinfoapp.presentation.viewModel.MainActivityViewModel
import com.example.populationinfoapp.ui.theme.PopulationInfoAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopulationInfoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val viewModel: MainActivityViewModel = viewModel()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSnackbar by viewModel.showSnackbar.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(
                    stringResource(id = R.string.app_name),
                    color = Color.White
                    ) },
                colors = topAppBarColors(
                    containerColor = Color(0xFF494848)
                )
            )
        },
        content = { paddingValues ->
            HomeContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                viewModel = viewModel
            )
        }
    )
    if(showSnackbar)
        ShowSnackBar(msg = "Check Internet Connectivity!", scope, snackBarHostState)
}