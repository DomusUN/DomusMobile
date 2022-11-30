package co.domus.domusmobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.domus.domusmobile.views.LoginScreen
import co.domus.domusmobile.views.RegisterScreen
import co.domus.domusmobile.viewModel.LoginViewModel
import co.domus.domusmobile.viewModel.RegisterViewModel
import co.domus.domusmobile.viewModel.ServiceViewModel
import co.domus.domusmobile.views.HomeScreen
import co.domus.domusmobile.views.RoleScreen

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
            val context = LocalContext.current
            LoginScreen(navController, LoginViewModel())
        }
        composable(route = DomusScreens.Register.route) {
            RegisterScreen(navController, RegisterViewModel())
        }
        composable(route = DomusScreens.Home.route) {
            HomeScreen(navController, ServiceViewModel())
        }
        composable(route = DomusScreens.Role.route){
            RoleScreen(navController)
        }
    }
}

