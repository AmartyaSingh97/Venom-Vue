package com.amartyasingh.venomvue.screens

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amartyasingh.venomvue.viewmodels.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(),
    onOpenCameraPreview: () -> Unit,
    onChoosePicture: () -> Unit,
    onCheckResults: () -> Unit,
) {

    val viewModel = mainViewModel

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        onOpenCamera = {
            onOpenCameraPreview()
        },
        onChoosePicture = onChoosePicture,
        onCheckResults = onCheckResults,
        image = viewModel.capturedImageUri
    )
}

@Composable
private fun MainContent(
    onOpenCamera: () -> Unit,
    onChoosePicture: () -> Unit,
    onCheckResults: () -> Unit,
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    image: Uri
) {

    if (hasPermission) {
        HomeScreen(
            image = image,
            onOpenCamera = onOpenCamera,
            onChoosePicture = onChoosePicture,
            onCheckResults = onCheckResults
        )
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}