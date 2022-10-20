package co.domus.domusmobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.domus.domusmobile.views.LoginScreen
import co.domus.domusmobile.views.ServiceViewModel
import co.domus.domusmobile.views.WelcomeScreen

@Composable
fun DomusApp(modifier: Modifier = Modifier, viewModel: ServiceViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DomusScreens.Start.route
    ) {
        composable(route = DomusScreens.Start.route) {
            WelcomeScreen(navController)
        }
        composable(route = DomusScreens.Login.route) {
            val context = LocalContext.current
            LoginScreen(navController)
        }
        composable(route = DomusScreens.Register.route) {

        }
        composable(route = DomusScreens.Home.route) {

        }
    }
}