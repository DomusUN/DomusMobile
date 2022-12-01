package co.domus.domusmobile.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.domus.domusmobile.model.ServiceList

class RegisterWorkerViewModel : ViewModel() {

    private val _numberId = MutableLiveData<String>()
    val numberId: LiveData<String> = _numberId

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _serviceList = mutableStateListOf(
        ServiceList(name = "Aseo", isSelected = false),
        ServiceList(name = "Carpinteria", isSelected = false),
        ServiceList(name = "Plomeria", isSelected = false),
        ServiceList(name = "Electricista", isSelected = false),
    )
    val serviceList: List<ServiceList> = _serviceList

    fun setServiceSelectedAtIndex(index: Int, isSelected: Boolean) {
        _serviceList[index] = _serviceList[index].copy(isSelected = isSelected)
    }

    fun onRegisterChanged(numberId: String, description : String){
        _numberId.value = numberId
        _description.value = description
    }

    fun onPriceChanged(index: Int, price: String){
        if (price.isEmpty()){
            _serviceList[index] = _serviceList[index].copy(price = 0)
        }else{
            _serviceList[index] = _serviceList[index].copy(price = price.toLong())
        }
    }
}