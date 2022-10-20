package co.domus.domusmobile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.domus.domusmobile.views.LoginScreen
import co.domus.domusmobile.views.ServiceViewModel
import co.domus.domusmobile.views.WelcomeScreen

/**
 * enum values that represent the screens in the app
 */
enum class DomusScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Home(title = R.string.home)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun DomusAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun DomusApp(modifier: Modifier = Modifier, viewModel: ServiceViewModel = viewModel()) {
    val navController = rememberNavController()

    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen

    Scaffold(
        topBar = {
            DomusAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = DomusScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = DomusScreen.Start.name) {
                WelcomeScreen()
            }
            composable(route = DomusScreen.Login.name) {
                val context = LocalContext.current
                LoginScreen()
            }
            composable(route = DomusScreen.Register.name) {

            }
            composable(route = DomusScreen.Home.name) {

            }
        }
    }
}