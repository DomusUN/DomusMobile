package co.domus.domusmobile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.R
import co.domus.domusmobile.model.Service
import co.domus.domusmobile.navigation.DomusScreens
import co.domus.domusmobile.viewModel.ServiceViewModel
import java.util.*

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
        Spacer(modifier = Modifier.padding(16.dp))

        val textState = remember { mutableStateOf(TextFieldValue("")) }

        SearchBar(Modifier.align(Alignment.CenterHorizontally), textState)
        Spacer(modifier = Modifier.padding(16.dp))
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
            tint = MaterialTheme.colorScheme.primary
        )
        Text(text = "Tu dirección", Modifier.padding(start = 10.dp), color = MaterialTheme.colorScheme.onBackground)
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
            Text("¿Qué serivicio estas buscando?")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun ServicesList(serviceViewModel: ServiceViewModel, state: MutableState<TextFieldValue>){
    var filteredServices: List<Service>
    val context = LocalContext.current
    LazyColumn {
        //val searchedText = state.value.text
        /*
        serviceViewModel.getServiceList()
        filteredServices = serviceViewModel.serviceListResponse
        itemsIndexed(filteredServices) { _, service ->
            ServiceItem(service, LocalContext.current)
        }
         */
        val numbers = listOf(1,2,3,4,5,6,7,8,9)
        itemsIndexed(numbers) { _, num ->
            val service = Service(num.toString(),"ASEO","housework")
            ServiceItem(service, LocalContext.current)
        }
    }
}