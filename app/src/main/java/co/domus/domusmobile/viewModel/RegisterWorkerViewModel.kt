package co.domus.domusmobile.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.domus.domusmobile.model.*
import co.domus.domusmobile.network.APIService
import kotlinx.coroutines.launch

class RegisterWorkerViewModel : ViewModel() {

    var userCreateResponse: String by mutableStateOf("")

    private val _numberId = MutableLiveData<String>()
    val numberId: LiveData<String> = _numberId

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    private val _serviceList = mutableStateListOf<ServiceList>()
    val serviceList: List<ServiceList> = _serviceList

    private val _sucessRegister = MutableLiveData<Boolean>()
    val sucessRegister: LiveData<Boolean> = _sucessRegister

    private val _placesList = mutableStateListOf(
        PlacesList(id = 1, name = "Usaquen", isSelected = false),
        PlacesList(id = 2, name = "Chapinero", isSelected = false),
        PlacesList(id = 3, name = "Santafe", isSelected = false),
    )
    val placesList: List<PlacesList> = _placesList

    fun fillServiceList(listService: List<Service>){
        listService.forEachIndexed { _, service ->
            _serviceList.add(ServiceList(name = service.name, isSelected = false, id = service.service_id))
        }
    }

    fun setServiceSelectedAtIndex(index: Int, isSelected: Boolean) {
        if(!isSelected){
            _serviceList[index] = _serviceList[index].copy(price = 0)
        }
        _serviceList[index] = _serviceList[index].copy(isSelected = isSelected)
        verifyData()
    }

    fun setPlaceSelectedAtIndex(index: Int, isSelected: Boolean) {
        _placesList[index] = _placesList[index].copy(isSelected = isSelected)
        verifyData()
    }

    fun onRegisterChanged(numberId: String, description : String){
        _numberId.value = numberId
        _description.value = description
        verifyData()
    }

    fun onPriceChanged(index: Int, price: String){
        if (price.isEmpty()){
            _serviceList[index] = _serviceList[index].copy(price = 0)
        }else{
            _serviceList[index] = _serviceList[index].copy(price = price.toInt())
        }
        verifyData()
    }

    fun verifyData(){
        var countServices = 0
        var countPlaces = 0

        _serviceList.forEachIndexed { _, serviceList ->
            if(serviceList.isSelected && serviceList.price > 0){
                countServices++
            }
        }

        _placesList.forEachIndexed { _, placesList ->
            if(placesList.isSelected){
                countPlaces++
            }
        }
        _registerEnable.value = countServices > 0 && countPlaces > 0 && _numberId.value?.isNotEmpty() == true && _description.value?.isNotEmpty() == true
    }

    fun addRoleWorker(worker: Worker, idUser: String, context: Context){
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val response = apiService.createWorkerRole(idUser, worker)
                userCreateResponse = response.ID
                if(userCreateResponse.isNotEmpty()){
                    _sucessRegister.value = true
                    Toast.makeText(
                        context,
                        "Sucess register worker",
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