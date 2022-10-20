package co.domus.domusmobile.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.navigation.DomusScreens

@Composable
fun LoginScreen(navController: NavController) {
    LoginUI(navController)
}

@Composable
fun LoginUI(navController: NavController) {

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    var emailError = false
    var passwordError = false

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                textAlign = TextAlign.Left,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Correo electrónico") },
                placeholder = { Text(text = "Email") },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),
                isError = emailError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                placeholder = { Text(text = "Contraseña") },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),
                isError = passwordError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            ClickableText(text = AnnotatedString("Olvidaste tu contraseña"), onClick = {

            }, style = TextStyle(color = Color.Blue), modifier = Modifier.padding(vertical = 20.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                onClick = {
                    if (email.value.text.isEmpty() && password.value.text.isNotEmpty()) {
                        emailError = true
                    } else if (email.value.text.isNotEmpty() && password.value.text.isEmpty()) {
                        passwordError = true
                    } else if (email.value.text.isEmpty() && password.value.text.isEmpty()) {
                        emailError = true
                        passwordError = true
                    } else {
                        verifyLogin(email.value.toString(), password.value.toString(), context)
                    }
                }) {
                Text(
                    text = "Ingresa",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text("¿No tienes cuenta?")
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            ClickableText(text = AnnotatedString("Registrate aqui"), onClick = {
                navController.navigate(route = DomusScreens.Register.route)
            }, style = TextStyle(color = Color.Blue))
        }
    }
}

fun verifyLogin(email: String, password: String, context: Context) {
    Toast.makeText(context, "Ingresando...", Toast.LENGTH_LONG).show()
}



