package com.amartyasingh.venomvue.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var capturedImageUri: Uri by mutableStateOf(Uri.EMPTY)

    fun updateCapturedImageUri(uri: Uri) {
        capturedImageUri = uri
    }
}