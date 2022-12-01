package co.domus.domusmobile.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.Client
import co.domus.domusmobile.network.APIService
import kotlinx.coroutines.launch

class RoleViewModel: ViewModel() {
    val options = listOf( "Selecciona tu rol", "Cliente", "Trabajador" )
    var userCreateResponse: String by mutableStateOf("")

    private val _selectedOptionText = MutableLiveData<String>()
    val selectedOptionText: LiveData<String> = _selectedOptionText

    private val _sucessRegister = MutableLiveData<Boolean>()
    val sucessRegister: LiveData<Boolean> = _sucessRegister

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    fun onRoleChanged(selectedOption: String){
        _selectedOptionText.value = selectedOption
        _registerEnable.value = true
    }

    fun addRoleClient(idUser: String, context: Context){
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                var client = Client(mutableListOf<String>())
                val response = apiService.createClientRole(idUser, client)
                println(response)
                userCreateResponse = response.ID
                if(userCreateResponse.isNotEmpty()){
                    _sucessRegister.value = true
                    Toast.makeText(
                        context,
                        "Sucess register client",
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

