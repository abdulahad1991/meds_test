package com.demo.medstest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demo.medstest.ui.LoginScreen
import com.demo.medstest.ui.MedicineDetailScreen
import com.demo.medstest.ui.MedicineListScreen
import com.demo.medstest.viewmodel.LoginViewModel
import com.demo.medstest.viewmodel.MedicineViewModel

@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { navController.navigate("medicines/${viewModel.username.value}") }
            )
        }

        composable(
            route = "medicines/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val viewModel: MedicineViewModel = hiltViewModel()
            MedicineListScreen(
                viewModel = viewModel,
                username = username,
                onMedicineClick = { medicine ->
                    navController.navigate("medicine_detail/${medicine.id}")
                }
            )
        }
        composable(route = "medicine_detail/{medicineId}",
            arguments = listOf(navArgument("medicineId") { type = NavType.StringType })
        ) { backStackEntry ->
            val medicineId = backStackEntry.arguments?.getString("medicineId")
            val viewModel: MedicineViewModel = hiltViewModel()
            MedicineDetailScreen(
                viewModel = viewModel,
                medicineId = medicineId ?: "",
                onBack = { navController.navigateUp() }
            )
        }
    }
}