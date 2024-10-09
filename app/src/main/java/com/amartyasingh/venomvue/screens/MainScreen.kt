package com.amartyasingh.venomvue.screens

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amartyasingh.venomvue.viewmodels.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    onOpenCameraPreview: () -> Unit,
    onCheckResults: () -> Unit,
) {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        onOpenCamera = {
            onOpenCameraPreview()
        },
        onCheckResults = onCheckResults,
        viewModel = mainViewModel
    )
}

@Composable
private fun MainContent(
    onOpenCamera: () -> Unit,
    onCheckResults: () -> Unit,
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    viewModel: MainViewModel
) {

    if (hasPermission) {
        HomeScreen(
            onOpenCamera = onOpenCamera,
            onCheckResults = onCheckResults,
            viewModel = viewModel
        )
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}