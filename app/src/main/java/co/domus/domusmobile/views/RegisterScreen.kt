package co.domus.domusmobile.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import co.domus.domusmobile.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            RegisterBody(navController, viewModel)
        }
    }
}

@Composable
fun RegisterBody(navController: NavController, viewModel: RegisterViewModel) {
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val verifyPassword : String by viewModel.verifyPassword.observeAsState(initial = "")
    var names by remember { mutableStateOf(TextFieldValue()) }
    var surnames by remember { mutableStateOf(TextFieldValue()) }
    var contactNumber by remember { mutableStateOf(TextFieldValue()) }
    var address by remember { mutableStateOf(TextFieldValue()) }
    val registerEnable : Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            Row {
                Text(text = "Bienvenido a ")
                Text(text = "Domus", color = Color.Blue)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(text = "Registro cliente ", style = MaterialTheme.typography.h3)
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
                    onValueChange = { viewModel.onLoginChanged(it, password, verifyPassword) },
                    label = { Text("Correo electronico") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = names,
                    onValueChange = { names = it },
                    label = { Text("Nombres") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = surnames,
                    onValueChange = { surnames = it },
                    label = { Text("Apellidos") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = contactNumber,
                    onValueChange = { contactNumber = it },
                    label = { Text("Numero de contacto") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección") },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spaceTextFields)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.onLoginChanged(email, it, verifyPassword) },
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
                    onValueChange = { viewModel.onLoginChanged(email, password, it) },
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
                    },
                    enabled = registerEnable
                ) {
                    Text(text = "Registra")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

