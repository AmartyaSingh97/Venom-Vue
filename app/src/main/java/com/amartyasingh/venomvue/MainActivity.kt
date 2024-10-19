package com.amartyasingh.venomvue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amartyasingh.venomvue.ui.screens.CameraPreviewScreen
import com.amartyasingh.venomvue.ui.screens.MainScreen
import com.amartyasingh.venomvue.ui.theme.VenomVueTheme
import com.amartyasingh.venomvue.viewmodels.MainViewModel
import com.amartyasingh.venomvue.viewmodels.SplashViewmodel

class MainActivity : ComponentActivity() {
    private val viewModel : SplashViewmodel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setContent {
            val temp = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(temp) }

            VenomVueTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main_screen"){

                        composable("main_screen"){
                            MainScreen(
                                darkTheme = darkTheme,
                                mainViewModel = mainViewModel,
                                onOpenCameraPreview = {
                                    navController.navigate("camera_preview")
                                },
                                onCheckResults = {},
                                onThemeUpdated = { darkTheme = !darkTheme }
                            )
                        }

                        composable("camera_preview"){
                            CameraPreviewScreen(onImageCaptured = { image ->
                                // Do something with the image
                                mainViewModel.updateCapturedImageUri(image)
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isSplashShow.value
            }
        }
    }
}

