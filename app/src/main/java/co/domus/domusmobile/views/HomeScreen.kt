package co.domus.domusmobile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.model.Service
import co.domus.domusmobile.viewModel.ServiceViewModel

@Composable
fun HomeScreen(navController: NavController, serviceViewModel: ServiceViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        HomeUI(navController, serviceViewModel, Modifier.align(Alignment.Center))
    }
}

@Composable
fun HomeUI(navController: NavController, serviceViewModel: ServiceViewModel, modifier: Modifier) {
    Column(
        modifier
    ) {
        Direction()
        Spacer(modifier = Modifier.padding(8.dp))

        val textState = remember { mutableStateOf(TextFieldValue("")) }

        SearchBar(Modifier.align(Alignment.CenterHorizontally), textState)
        Spacer(modifier = Modifier.padding(8.dp))
        ServicesList(serviceViewModel, textState)
    }
}

@Composable
fun Direction() {
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "location",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp)
        )
        Text(text = "Tu dirección", Modifier.padding(start = 10.dp), fontSize = 30.sp, color = MaterialTheme.colorScheme.onBackground)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier, state: MutableState<TextFieldValue>){
    OutlinedTextField(
        value = state.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search bar") },
        onValueChange = { value ->
            state.value = value
        },
        textStyle = TextStyle(fontSize = 18.sp),
        placeholder = {
            Text("¿Qué servicio estas buscando?")
        },
        modifier = modifier
            .heightIn(min = 56.dp)
    )
}

@Composable
fun ServicesList(serviceViewModel: ServiceViewModel, state: MutableState<TextFieldValue>){
    serviceViewModel.getServiceList()
    val serviceList : List<Service> = serviceViewModel.serviceListResponse
    if(serviceList.isNotEmpty()){
        LazyColumn{
            itemsIndexed(serviceList) { _, ser ->
                val service = Service(ser.ID, ser.name,ser.image_name,ser.service_id)
                ServiceItem(service, LocalContext.current)
            }
        }
    }
}