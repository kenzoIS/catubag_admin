package com.example.adminlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A1A))) {

        // --- Top Header Section with Image ---
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f)) {

            // IMPORTANT: Make sure 'login_bg' matches your image file name exactly
            Image(
                painter = painterResource(id = R.drawable.login_bg),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // The Reddish Tint Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF8B5A5A).copy(alpha = 0.5f))
            )

            // 4. THE NEW LOGO IMAGE! Add this right here:
            Image(
                painter = painterResource(id = R.drawable.logo_h), // Make sure this matches your logo file name
                contentDescription = "App Logo",
                modifier = Modifier
                    .align(Alignment.BottomStart) // This pins it to the bottom-left of the image header
                    .padding(start = 15.dp, bottom = 50.dp) // Pushes it away from the absolute edges
                    .size(70.dp)
            )// Adjust this number to make the logo bigger or smaller
        }

        // --- Bottom Sheet Section ---
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
                Text(
                    text = "Welcome, Admin!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 40.dp)
                )

                Text(
                    text = "Login to continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                // --- Email Input (With Floating Label & Black Border) ---
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

                // --- Password Input (With Floating Label & Black Border) ---
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

                // --- Login Button (With Drop Shadow Elevation) ---
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