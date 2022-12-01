package co.domus.domusmobile.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.domus.domusmobile.model.ServicesWorker
import co.domus.domusmobile.model.Worker
import co.domus.domusmobile.navigation.DomusScreens
import co.domus.domusmobile.viewModel.RegisterWorkerViewModel
import co.domus.domusmobile.viewModel.ServiceViewModel

@Composable
fun RegisterWorkerScreen(
    navController: NavController,
    registerWorkerViewModel: RegisterWorkerViewModel,
    serviceViewModel: ServiceViewModel,
    idWorker: String?
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            RegisterWorkerBody(navController, registerWorkerViewModel, serviceViewModel, idWorker)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterWorkerBody(
    navController: NavController,
    registerWorkerViewModel: RegisterWorkerViewModel,
    serviceViewModel: ServiceViewModel,
    idWorker: String?
) {
    val numberId: String by registerWorkerViewModel.numberId.observeAsState(initial = "")
    val description: String by registerWorkerViewModel.description.observeAsState(initial = "")
    val registerEnable: Boolean by registerWorkerViewModel.registerEnable.observeAsState(initial = false)
    val sucessRegister : Boolean by registerWorkerViewModel.sucessRegister.observeAsState(initial = false)

    val context = LocalContext.current

    if(registerWorkerViewModel.serviceList.isEmpty()){
        serviceViewModel.getServiceList()
        registerWorkerViewModel.fillServiceList(serviceViewModel.serviceListResponse)
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            Row {
                Text(text = "Bienvenido a ")
                Text(text = "Domus", color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(text = "Registro trabajador ", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val spaceTextFields = 18.dp
                OutlinedTextField(
                    value = numberId,
                    onValueChange = { registerWorkerViewModel.onRegisterChanged(it, description) },
                    label = { Text("Cedula") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { registerWorkerViewModel.onRegisterChanged(numberId, it) },
                    label = { Text("Descripcion") },
                    minLines = 3,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )

                Spacer(modifier = Modifier.padding(bottom = spaceTextFields))
                Text(
                    text = "Selecciona los servicios que quieres ofrecer",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(bottom = spaceTextFields))
                ServicesList(registerWorkerViewModel, context)
                Spacer(modifier = Modifier.padding(bottom = spaceTextFields))
                Text(
                    text = "Selecciona las localidades que atiendes que atiendes",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(bottom = spaceTextFields))
                PlacesList(registerWorkerViewModel)
                Spacer(modifier = Modifier.padding(bottom = spaceTextFields))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(45.dp),
                    onClick = {
                        val places = mutableListOf<Int>()
                        registerWorkerViewModel.placesList.forEachIndexed { _, placesList ->
                            if(placesList.isSelected){
                                places.add(placesList.id)
                            }
                        }
                        val reviews = mutableListOf<String>()
                        val arrServices = mutableListOf<ServicesWorker>()
                        registerWorkerViewModel.serviceList.forEachIndexed { _, serviceList ->
                            if(serviceList.isSelected){
                                arrServices.add(ServicesWorker(serviceList.id, 0.0F, serviceList.price))
                            }
                        }
                        val newWorker = Worker(
                            numberId,
                            description,
                            0.0F,
                            places,
                            reviews,
                            arrServices
                        )
                        if (idWorker != null) {
                            registerWorkerViewModel.addRoleWorker(newWorker,idWorker,context)
                        }
                    },
                    enabled = registerEnable
                ) {
                    Text(text = "Finalizar")
                }

                Spacer(modifier = Modifier.height(20.dp))

                LaunchedEffect(key1 = sucessRegister){
                    if (sucessRegister){
                        navController.navigate(route = DomusScreens.Login.route)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesList(registerWorkerViewModel: RegisterWorkerViewModel, context: Context) {
    Column(modifier = Modifier.fillMaxHeight(0.7f)) {
        registerWorkerViewModel.serviceList.forEachIndexed { i, service ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = service.isSelected,
                            onCheckedChange = {
                                registerWorkerViewModel.setServiceSelectedAtIndex(i, it)

                            },
                            colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary)
                        )
                        Text(
                            text = service.name,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Row(horizontalArrangement = Arrangement.End) {
                        var serviceValueText = ""
                        serviceValueText = if (service.price == 0) {
                            ""
                        } else {
                            service.price.toString()
                        }
                        val serviceAlpha = if (service.isSelected) {
                            1f
                        } else {
                            0f
                        }
                        TextField(
                            value = serviceValueText,
                            onValueChange = {
                                if (it.isEmpty()) {
                                    registerWorkerViewModel.onPriceChanged(i, it)
                                } else if (it.toInt() <= 1000000) {
                                    registerWorkerViewModel.onPriceChanged(i, it)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Cannot be more than 1000000",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background),
                            textStyle = TextStyle(textAlign = TextAlign.Right),
                            label = { Text("Precio") },
                            placeholder = { Text(text = "Precio") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier
                                .width(170.dp)
                                .height(50.dp)
                                .alpha(serviceAlpha)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlacesList(registerWorkerViewModel: RegisterWorkerViewModel) {
    Column(modifier = Modifier.fillMaxHeight(0.7f)) {
        registerWorkerViewModel.placesList.forEachIndexed { i, place ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = place.isSelected,
                    onCheckedChange = {
                        registerWorkerViewModel.setPlaceSelectedAtIndex(i, it)

                    },
                    colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = place.name,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
            }
        }
    }
}