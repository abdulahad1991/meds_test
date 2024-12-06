package com.demo.medstest.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.medstest.viewmodel.MedicineViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(
    viewModel: MedicineViewModel,
    onBack: () -> Unit
) {
    val selectedMedicine by viewModel.selectedMedicine.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medicine Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        selectedMedicine?.let { medicine ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = medicine.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Dose: ${medicine.dose}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Strength: ${medicine.strength}",
                    style = MaterialTheme.typography.bodyLarge
                )

                medicine.description?.let { description ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Description: $description",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}