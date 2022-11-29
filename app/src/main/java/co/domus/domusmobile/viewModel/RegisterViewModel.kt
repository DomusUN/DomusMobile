package co.domus.domusmobile.viewModel

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.User
import co.domus.domusmobile.network.APIService
import co.domus.domusmobile.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory

class RegisterViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private val _sucessRegister = MutableLiveData<Boolean>()
    val sucessRegister: LiveData<Boolean> = _sucessRegister

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _verifyPassword = MutableLiveData<String>()
    val verifyPassword: LiveData<String> = _verifyPassword

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> = _surname

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    private var userCreateResponse: String by mutableStateOf("")

    fun onRegisterChanged(
        email: String,
        password: String,
        verifyPassword: String,
        name: String,
        surname: String,
        phone: String,
        address: String
    ) {
        _email.value = email
        _password.value = password
        _verifyPassword.value = verifyPassword
        _name.value = name
        _surname.value = surname
        _phone.value = phone
        _address.value = address
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

    fun createNewUser(user: User, context: Context) {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val response = apiService.createUser(user)
                userCreateResponse = response.ID
                if(userCreateResponse.isNotEmpty()){
                    _sucessRegister.value = true
                    Toast.makeText(
                        context,
                        "Sucess register",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    _sucessRegister.value = false
                    Toast.makeText(
                        context,
                        "Failed register backend",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}