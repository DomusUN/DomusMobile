package co.domus.domusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.User
import co.domus.domusmobile.network.APIService
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    var userCreateResponse:String by mutableStateOf("")
    var errorMessage: String by mutableStateOf("")

    fun createUser(user: User){
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val response = apiService.createUser(user)
                userCreateResponse = response.toString()
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}