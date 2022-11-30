package co.domus.domusmobile.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            RegisterBody(navController, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBody(navController: NavController, viewModel: RegisterViewModel) {
    val sucessRegister : Boolean by viewModel.sucessRegister.observeAsState(initial = false)
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val verifyPassword : String by viewModel.verifyPassword.observeAsState(initial = "")
    val names : String by viewModel.name.observeAsState(initial = "")
    val surnames : String by viewModel.surname.observeAsState(initial = "")
    val contactNumber : String by viewModel.phone.observeAsState(initial = "")
    val address : String by viewModel.address.observeAsState(initial = "")
    val registerEnable : Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            Row {
                Text(text = "Bienvenido a ")
                Text(text = "Domus", color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(text = "Registro cliente ", style = MaterialTheme.typography.bodyMedium)
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
                    value = email,
                    onValueChange = { viewModel.onRegisterChanged(it, password, verifyPassword, names, surnames, contactNumber, address) },
                    label = { Text("Correo electronico") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = names,
                    onValueChange = { viewModel.onRegisterChanged(email, password, verifyPassword, it, surnames, contactNumber, address) },
                    label = { Text("Nombres") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = surnames,
                    onValueChange = { viewModel.onRegisterChanged(email, password, verifyPassword, names, it, contactNumber, address) },
                    label = { Text("Apellidos") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = contactNumber,
                    onValueChange = { viewModel.onRegisterChanged(email, password, verifyPassword, names, surnames, it, address) },
                    label = { Text("Numero de contacto") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { viewModel.onRegisterChanged(email, password, verifyPassword, names, surnames, contactNumber, it) },
                    label = { Text("Dirección") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.onRegisterChanged(email, it, verifyPassword, names, surnames, contactNumber, address) },
                    label = { Text("Contraseña") },
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)

                )
                OutlinedTextField(
                    value = verifyPassword,
                    onValueChange = { viewModel.onRegisterChanged(email, password, it, names, surnames, contactNumber, address) },
                    label = { Text("Verificar contraseña") },
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields + 20.dp)
                )
                Button(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(45.dp),
                    onClick = {
                        viewModel.createUser(context)
                        val newUser = User(
                            email,
                            password,
                            names,
                            surnames,
                            contactNumber,
                            address
                        )
                        viewModel.createNewUser(newUser,context)
                    },
                    enabled = registerEnable
                ) {
                    Text(text = "Registra")
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

