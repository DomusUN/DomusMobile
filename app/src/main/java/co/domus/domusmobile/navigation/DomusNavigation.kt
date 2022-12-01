package co.domus.domusmobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.domus.domusmobile.viewModel.*
import co.domus.domusmobile.views.*

@Composable
fun DomusApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DomusScreens.Start.route
    ) {
        composable(route = DomusScreens.Start.route) {
            //WelcomeScreen(navController)
            HomeScreen(navController, ServiceViewModel())
        }
        composable(route = DomusScreens.Login.route) {
            LoginScreen(navController, LoginViewModel())
        }
        composable(route = DomusScreens.Register.route) {
            RegisterScreen(navController, RegisterViewModel())
        }
        composable(route = DomusScreens.Home.route) {
            HomeScreen(navController, ServiceViewModel())
        }
        composable(route = DomusScreens.WorkersList.route + "/{service}") { backStackEntry ->
            WorkersScreen(navController, UserViewModel(), backStackEntry.arguments?.getString("service"))
        }
        composable(route = DomusScreens.Role.route){
            RoleScreen(navController, RoleViewModel())
        }
        composable(route = DomusScreens.RegisterWorker.route){
            RegisterWorkerScreen(navController, RegisterWorkerViewModel())
        }
    }
}

