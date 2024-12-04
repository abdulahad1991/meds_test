package com.demo.medstest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    Column {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        Button(onClick = { navController.navigate("dashboard/$username") }) {
            Text("Login")
        }
    }
}