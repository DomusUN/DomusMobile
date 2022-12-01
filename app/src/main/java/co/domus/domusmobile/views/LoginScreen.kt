package co.domus.domusmobile.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.navigation.DomusApp
import co.domus.domusmobile.navigation.DomusScreens
import co.domus.domusmobile.ui.DomusTheme
import co.domus.domusmobile.viewModel.LoginViewModel

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
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged:(String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        label = { Text("Contraseña") },
        placeholder = { Text(text = "Contraseña") },
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
    val context = LocalContext.current
    ClickableText(
        text = AnnotatedString("Olvidaste tu contraseña"), onClick = { Toast.makeText(
            context,
            "Failed register backend",
            Toast.LENGTH_SHORT
        ).show() },
        style = TextStyle(color = MaterialTheme.colorScheme.onTertiaryContainer),
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
        colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
    Text("¿No tienes cuenta?", modifier = modifier, style = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 15.sp))
    Spacer(modifier = Modifier.padding(2.dp))
    ClickableText(text = AnnotatedString("Registrate aqui"), onClick = {
        navController.navigate(route = DomusScreens.Register.route)
    }, style = TextStyle(color = MaterialTheme.colorScheme.error, fontSize = 18.sp), modifier = modifier)
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DomusTheme {
        DomusApp()
    }
}

