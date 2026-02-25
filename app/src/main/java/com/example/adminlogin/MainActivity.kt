package com.example.adminlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // --- CAROUSEL SETUP ---
    // IMPORTANT: Change these to match the exact names of your 4 files in the drawable folder!
    val bgImages = listOf(
        R.drawable.login_bg, // Image 1
        R.drawable.login_bg1,      // Image 2
        R.drawable.login_bg2,      // Image 3
        R.drawable.login_bg3       // Image 4
    )

    var currentImageIndex by remember { mutableIntStateOf(0) }

    // This creates the automatic timer loop
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Waits 3 seconds before changing the image (adjust as you like!)
            currentImageIndex = (currentImageIndex + 1) % bgImages.size
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A1A))) {

        // --- 1. Top Header Section (Carousel Background) ---
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f)) {

            // The Crossfade smoothly animates the transition between the images
            Crossfade(
                targetState = bgImages[currentImageIndex],
                animationSpec = tween(durationMillis = 1000), // 1 second fade animation
                label = "Background Carousel"
            ) { targetImage ->
                Image(
                    painter = painterResource(id = targetImage),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Reddish Tint Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF8B5A5A).copy(alpha = 0.5f))
            )

            // The Logo
            Image(
                painter = painterResource(id = R.drawable.logo_h),
                contentDescription = "App Logo",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 25.dp, bottom = 50.dp) // Pushed closer to the left
                    .size(70.dp)
            )
        }

        // --- 2. Bottom Sheet Section (White Form) ---
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
            color = Color(0xFFFCFCFC)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Welcome, Admin!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 30.dp)
                )

                Text(
                    text = "Login to continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                // --- Email Input ---
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter your email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email", tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Gray
                    ),
                    singleLine = true
                )

                // --- Password Input ---
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Enter your Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password", tint = Color.Gray) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Gray
                    ),
                    singleLine = true
                )

                // --- Forgot Password ---
                Text(
                    text = "Forgot Password?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.End).padding(bottom = 30.dp)
                )

                // --- Login Button ---
                Button(
                    onClick = { /* Handle Login Action */ },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(55.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(25.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2A2A))
                ) {
                    Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}