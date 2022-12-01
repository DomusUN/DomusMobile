package co.domus.domusmobile.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoleViewModel: ViewModel() {
    val options = listOf( "Selecciona tu rol", "Cliente", "Trabajador" )

    private val _selectedOptionText = MutableLiveData<String>()
    val selectedOptionText: LiveData<String> = _selectedOptionText

    fun onRoleChanged(selectedOption: String){
        _selectedOptionText.value = selectedOption
    }

    //var selectedOptionText by remember { mutableStateOf(options[0]) }
}

