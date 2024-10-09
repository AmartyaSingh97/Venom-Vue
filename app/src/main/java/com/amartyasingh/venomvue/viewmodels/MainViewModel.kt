package com.amartyasingh.venomvue.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {

    val capturedImageUri = MutableStateFlow(Uri.EMPTY)

    fun updateCapturedImageUri(uri: Uri) {
        capturedImageUri.value = uri
    }
}