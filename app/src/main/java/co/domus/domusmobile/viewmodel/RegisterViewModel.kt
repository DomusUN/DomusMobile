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

class RegisterViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _verifyPassword = MutableLiveData<String>()
    val verifyPassword: LiveData<String> = _verifyPassword

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    fun onLoginChanged(email: String, password: String, verifyPassword: String) {
        _email.value = email
        _password.value = password
        _verifyPassword.value = verifyPassword
        _registerEnable.value =
            isValidEmail(email) && isValidPassword(password) && isValidVerifyPassword(verifyPassword)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidVerifyPassword(verifyPassword: String): Boolean = verifyPassword.length > 6

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!registerEnable.value!!) {
                throw IllegalArgumentException("email and password can not be empty")
            }

            if (password.value != verifyPassword.value) {
                throw IllegalArgumentException(
                    "Password do not match"
                )
            }

            repository.register(
                email.value!!,
                password.value!!
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "success register",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Failed register",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}