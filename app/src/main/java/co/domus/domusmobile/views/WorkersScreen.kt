package co.domus.domusmobile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.model.User
import co.domus.domusmobile.viewModel.UserViewModel

@Composable
fun WorkersScreen(navController: NavController, userViewModel: UserViewModel, service: String?) {
    Box(
    Modifier
    .fillMaxSize()
    .background(color = MaterialTheme.colorScheme.background)
    ) {
        WorkersUI(navController,userViewModel, Modifier.align(Alignment.TopCenter), service)
    }
}

@Composable
fun WorkersUI(navController: NavController, userViewModel: UserViewModel, modifier: Modifier, service: String?){
    Column(
        modifier
    ) {
        Direction()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleWorkers(Modifier.align(Alignment.CenterHorizontally))
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchBarWorkers(Modifier.padding(16.dp), textState)
        Spacer(modifier = Modifier.padding(8.dp))
        Workers(userViewModel, textState, service)
    }
}

@Composable
fun TitleWorkers(modifier: Modifier){
    Text(
        "Aqui podras filtrar a los mejores expertos segun tu necesidad",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWorkers(modifier: Modifier, state: MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = state.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search bar") },
        onValueChange = { value ->
            state.value = value
        },
        textStyle = TextStyle(fontSize = 18.sp),
        placeholder = {
            Text("Escribe el nombre de algun experto")
        },
        modifier = modifier
            .heightIn(min = 56.dp)
    )
}

@Composable
fun Workers(userViewModel: UserViewModel, state: MutableState<TextFieldValue>, service: String?) {
    userViewModel.getWorkersListByService(service!!)
    val workers: List<User> = userViewModel.workersListResponse
    if(workers.isNotEmpty()){
        LazyColumn{
            itemsIndexed(workers) { _, worker ->
                WorkerItem(worker)
            }
        }
    }
}