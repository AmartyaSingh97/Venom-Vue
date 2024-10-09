package com.amartyasingh.venomvue.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalSee
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.amartyasingh.venomvue.R
import com.amartyasingh.venomvue.ui.theme.VenomVueTheme
import com.amartyasingh.venomvue.viewmodels.MainViewModel

@Composable
fun HomeScreen(
    onOpenCamera: () -> Unit,
    onCheckResults: () -> Unit,
    viewModel: MainViewModel
) {
    var imageUri by remember {  mutableStateOf<Uri?>(null) }

    val image = viewModel.capturedImageUri.collectAsStateWithLifecycle().value

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
                viewModel.capturedImageUri.value = it
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier.size(400.dp).padding(top = 32.dp),
                colors = CardDefaults.cardColors(Color.LightGray)
            )
            {
                AsyncImage(
                    model = image,
                    contentDescription = "Captured Image",
                    contentScale = ContentScale.FillBounds,
                    error = painterResource(R.drawable.snake_svgrepo_com),
                    )
            }

            Button(
                onClick = { onOpenCamera() },
                modifier = Modifier.padding(top = 48.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
                ){
                Icon(imageVector = Icons.Default.LocalSee, contentDescription = "Camera",
                        tint = MaterialTheme.colorScheme.background
                    )
                Text(text = "Click Picture",
                      style = TextStyle(
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                      ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = {
                    galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                modifier = Modifier.padding(top = 32.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
            ){
                Icon(imageVector = Icons.Default.Image, contentDescription = "Camera",
                    tint = MaterialTheme.colorScheme.background
                )
                Text(text = "Choose Picture",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = { onCheckResults() },
                modifier = Modifier.padding(top = 32.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
            ){
                Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera",
                    tint = MaterialTheme.colorScheme.background
                )
                Text(text = "Check Results",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

        }

    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(onOpenCamera = {}, onCheckResults = {}, viewModel = MainViewModel())
}

