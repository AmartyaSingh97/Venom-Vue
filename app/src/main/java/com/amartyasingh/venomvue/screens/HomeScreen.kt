package com.amartyasingh.venomvue.screens

import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amartyasingh.venomvue.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    image: Uri,
    onOpenCamera: () -> Unit,
    onChoosePicture: () -> Unit,
    onCheckResults: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier.size(400.dp).padding(top = 32.dp),
                colors = CardDefaults.cardColors(Color.LightGray)
            )
            {
                if(image==Uri.EMPTY){
                    Image(
                        painter = painterResource(R.drawable.snake_svgrepo_com),
                        contentDescription = "Snake",
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    )
                }else{
                     GlideImage(
                        image,
                        contentDescription = "Snake",
                        modifier = Modifier.fillMaxSize()
                     )
                }


            }

            Button(
                onClick = { onOpenCamera() },
                modifier = Modifier.padding(top = 48.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3D84FF))
                ){
                Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera",
                        tint = Color.White
                    )
                Text(text = "Open Camera",
                      style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                      ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = { onChoosePicture() },
                modifier = Modifier.padding(top = 32.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3D84FF))
            ){
                Icon(imageVector = Icons.Default.Image, contentDescription = "Camera",
                    tint = Color.White
                )
                Text(text = "Choose Picture",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = { onCheckResults() },
                modifier = Modifier.padding(top = 32.dp).width(400.dp).width(64.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3D84FF))
            ){
                Text(text = "Check Results",
                    style = TextStyle(
                        color = Color.White,
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
    HomeScreen(onChoosePicture = {}, onOpenCamera = {}, onCheckResults = {}, image = Uri.EMPTY)
}

