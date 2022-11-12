package co.domus.domusmobile.viewmodel

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {
    val currentUser = repository.currentUser

    private val _sucessLogin = MutableLiveData<Boolean>()
    val sucessLogin: LiveData<Boolean> = _sucessLogin

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun onLoginSelected(context: Context) = viewModelScope.launch {
        try {
            if (!loginEnable.value!!) {
                throw IllegalArgumentException("email and password can not be empty")
            }

            repository.login(
                email.value!!,
                password.value!!
            ) { isSuccessful ->
                if (isSuccessful) {
                    _sucessLogin.value = true
                    Toast.makeText(
                        context,
                        "success login",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    _sucessLogin.value = false
                    Toast.makeText(
                        context,
                        "Failed login",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}