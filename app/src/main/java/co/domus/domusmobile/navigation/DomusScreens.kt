package co.domus.domusmobile.navigation

import androidx.annotation.StringRes
import co.domus.domusmobile.R

/**
 * enum values that represent the screens in the app
 */
enum class DomusScreens(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Home(title = R.string.home)
}