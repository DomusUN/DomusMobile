package co.domus.domusmobile.views

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.domus.domusmobile.model.User
import co.domus.domusmobile.navigation.DomusScreens
import co.domus.domusmobile.viewModel.RegisterViewModel

@Composable
fun RoleScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            RoleBody(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleBody(navController: NavController) {
    /*val sucessRegister : Boolean by viewModel.sucessRegister.observeAsState(initial = false)
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val verifyPassword : String by viewModel.verifyPassword.observeAsState(initial = "")
    val names : String by viewModel.name.observeAsState(initial = "")
    val surnames : String by viewModel.surname.observeAsState(initial = "")
    val contactNumber : String by viewModel.phone.observeAsState(initial = "")
    val address : String by viewModel.address.observeAsState(initial = "")
    val registerEnable : Boolean by viewModel.registerEnable.observeAsState(initial = false)*/
    val context = LocalContext.current
    val options = listOf("Selecciona tu rol","Cliente", "Trabajador")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            Row {
                Text(text = "Bienvenido a ")
                Text(text = "Domus", color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(text = "Rol ", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // We want to react on tap/press on TextField to show menu
                Spacer(modifier = Modifier.padding(40.dp))

                Text(text = "¿Que eres? ", style = MaterialTheme.typography.titleMedium)

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    TextField(
                        // The `menuAnchor` modifier must be passed to the text field for correctness.
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(170.dp))

                Button(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(45.dp),
                    onClick = {

                    },
                    //enabled = registerEnable
                ) {
                    Text(text = "Registra")
                }
                Spacer(modifier = Modifier.height(20.dp))

                /*LaunchedEffect(key1 = sucessRegister){
                    if (sucessRegister){
                        navController.navigate(route = DomusScreens.Login.route)
                    }
                }*/
            }
        }
    }
}

