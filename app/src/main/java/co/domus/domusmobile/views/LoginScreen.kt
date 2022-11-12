package co.domus.domusmobile.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.navigation.DomusScreens
import co.domus.domusmobile.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LoginUI(navController, viewModel, Modifier.align(Alignment.Center))
    }
}

@Composable
fun LoginUI(navController: NavController, viewModel: LoginViewModel, modifier: Modifier) {

    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable : Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val sucessLogin : Boolean by viewModel.sucessLogin.observeAsState(initial = false)
    val context = LocalContext.current

    Column(
        modifier
    ) {
        HeaderTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password) { viewModel.onLoginChanged(email, it) }
        Spacer(modifier = Modifier.padding(16.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(loginEnable) {viewModel.onLoginSelected(context)}
        Spacer(modifier = Modifier.padding(30.dp))
        RegisterSection(navController, Modifier.align(Alignment.CenterHorizontally))

        LaunchedEffect(key1 = sucessLogin){
            if (sucessLogin){
                navController.navigate(route = DomusScreens.Home.route)
            }
        }
    }
}

@Composable
fun HeaderTitle(modifier: Modifier) {
    Text(
        text = "Login",
        textAlign = TextAlign.Left,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged:(String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        label = { Text(text = "Correo electronico") },
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Gray,
            placeholderColor = Color.Gray
        ),
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged:(String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        label = { Text("Contraseña") },
        placeholder = { Text(text = "Contraseña") },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Gray,
            placeholderColor = Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    ClickableText(
        text = AnnotatedString("Olvidaste tu contraseña"), onClick = { TODO() },
        style = TextStyle(color = Color.Blue),
        modifier = modifier
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    OutlinedButton(
        onClick = {
            onLoginSelected()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary,
        disabledBackgroundColor = Color(0xFFB5DEFA),
        contentColor = Color.White,
        disabledContentColor = Color.White),
        enabled = loginEnable,
    ) {
        Text(
            text = "Ingresa",
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun RegisterSection(navController: NavController, modifier: Modifier) {
    Text("¿No tienes cuenta?", modifier = modifier)
    Spacer(modifier = Modifier.padding(2.dp))
    ClickableText(text = AnnotatedString("Registrate aqui"), onClick = {
        navController.navigate(route = DomusScreens.Register.route)
    }, style = TextStyle(color = Color.Blue), modifier = modifier)
}



