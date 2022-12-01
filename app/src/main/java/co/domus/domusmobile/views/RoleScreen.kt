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
import co.domus.domusmobile.viewModel.RoleViewModel

@Composable
fun RoleScreen(navController: NavController, roleViewModel: RoleViewModel,
               idUser: String?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            RoleBody(navController, roleViewModel, idUser)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleBody(navController: NavController, roleViewModel: RoleViewModel,
             idUser: String?) {
    val context = LocalContext.current

    val sucessRegister : Boolean by roleViewModel.sucessRegister.observeAsState(initial = false)
    val selectedOptionText: String by roleViewModel.selectedOptionText.observeAsState(initial = roleViewModel.options[0])
    val registerEnable : Boolean by roleViewModel.registerEnable.observeAsState(initial = false)
    var expanded by remember { mutableStateOf(false) }


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
                        roleViewModel.options.forEachIndexed { index, selectionOption ->
                            if (index != 0) {
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        roleViewModel.onRoleChanged(selectionOption)
                                        expanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(170.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(45.dp),
                    onClick = {
                        if(selectedOptionText == "Cliente"){
                            if (idUser != null) {
                                roleViewModel.addRoleClient(idUser, context)
                            }
                        }
                    },
                    enabled = registerEnable
                ) {
                    if (selectedOptionText == "Cliente") {
                        Text(text = "Finalizar")
                    } else {
                        Text(text = "Siguiente")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                LaunchedEffect(key1 = sucessRegister){
                    if (sucessRegister){
                        if(selectedOptionText == "Cliente"){
                            navController.navigate(route = DomusScreens.Login.route)
                        }else{
                            navController.navigate(route = DomusScreens.RegisterWorker.route + "/" + idUser)
                        }

                    }
                }
            }
        }
    }
}

