package com.demo.medstest.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.medstest.data.model.Medicine
import com.demo.medstest.viewmodel.LoginViewModel
import com.demo.medstest.viewmodel.MedicineViewModel


@Composable
fun MedicineListScreen(
    viewModel: MedicineViewModel,
    username: String,
    onMedicineClick: (Medicine) -> Unit
) {
    val medicines by viewModel.medicines.collectAsState()
    val loginViewModel: LoginViewModel = hiltViewModel()
//    val username by loginViewModel.username.collectAsState()
    val greeting = loginViewModel.getGreeting()

    Column(modifier = Modifier.fillMaxSize()) {
        // Greeting and User Info
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "$greeting, $username!",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Welcome to your Medical Dashboard",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Medicine List
        LazyColumn {
            items(medicines) { medicine ->
                MedicineListItem(
                    medicine = medicine,
                    onClick = { onMedicineClick(medicine) }
                )
            }
        }
    }
}



@Composable
fun MedicineListItem(
    medicine: Medicine,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = medicine.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Dose: ${medicine.dose}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Strength: ${medicine.strength}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}