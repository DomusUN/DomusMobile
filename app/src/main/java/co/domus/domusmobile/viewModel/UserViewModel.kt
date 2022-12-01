package co.domus.domusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.User
import co.domus.domusmobile.network.APIService
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var workersListResponse:List<User> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getWorkersListByService(service: String){
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val workersList = apiService.getWorkersByService(service)
                workersListResponse = workersList
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}