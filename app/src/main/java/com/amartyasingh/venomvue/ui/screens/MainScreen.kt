package com.amartyasingh.venomvue.ui.screens

import androidx.compose.runtime.Composable
import com.amartyasingh.venomvue.viewmodels.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    darkTheme: Boolean,
    mainViewModel: MainViewModel,
    onOpenCameraPreview: () -> Unit,
    onCheckResults: () -> Unit,
    onThemeUpdated: () -> Unit
) {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        onOpenCamera = {
            onOpenCameraPreview()
        },
        onCheckResults = onCheckResults,
        viewModel = mainViewModel,
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated
    )
}

@Composable
private fun MainContent(
    onOpenCamera: () -> Unit,
    onCheckResults: () -> Unit,
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    viewModel: MainViewModel,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    if (hasPermission) {
        HomeScreen(
            onOpenCamera = onOpenCamera,
            onCheckResults = onCheckResults,
            viewModel = viewModel,
            darkTheme = darkTheme,
            onThemeUpdated = onThemeUpdated
        )
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}