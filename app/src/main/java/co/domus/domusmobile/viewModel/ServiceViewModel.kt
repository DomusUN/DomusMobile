package co.domus.domusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.Service
import co.domus.domusmobile.network.APIService
import kotlinx.coroutines.launch

class ServiceViewModel : ViewModel() {
    var serviceListResponse:List<Service> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getServiceList(){
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val serviceList = apiService.getServices()
                serviceListResponse = serviceList
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}