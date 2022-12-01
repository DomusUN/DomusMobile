package co.domus.domusmobile.navigation

sealed class DomusScreens(val route: String) {
    object Start : DomusScreens("Start")
    object Login : DomusScreens("Login")
    object Register : DomusScreens("Register")
    object Home : DomusScreens("Home")
    object WorkersList : DomusScreens("Workers")
    object Role : DomusScreens("Role")
    object RegisterWorker : DomusScreens("RegisterWorker")
}