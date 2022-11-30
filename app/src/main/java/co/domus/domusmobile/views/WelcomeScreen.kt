package co.domus.domusmobile.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import co.domus.domusmobile.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.domus.domusmobile.navigation.DomusScreens

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            /*HomeTitle()
            HomeBody(navController)*/
            RoleScreen(navController)
        }
    }
}

@Composable
fun HomeTitle() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(
                MaterialTheme.colorScheme.tertiary
            )
    ) {
        Text(
            text = "Te damos la bienvenida a",
            color = MaterialTheme.colorScheme.onTertiary,
            maxLines = 2,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 45.dp, vertical = 30.dp)
        )
        Text(
            text = "DOMUS",
            color = MaterialTheme.colorScheme.onTertiary,
            maxLines = 3,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = maxHeight * 0.15f, horizontal = 40.dp)
        )
        val painter = painterResource(R.drawable.homeimg)
        val multiplierY: Float
        val multiplierX: Float
        val imageSize: Dp
        if(maxHeight >= 300.dp){
            multiplierX = 0.1f
            multiplierY = 0.3f
            imageSize = 230.dp
        }else{
            multiplierX = 0.05f
            multiplierY = 0.2f
            imageSize = 180.dp
        }

        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(maxWidth * multiplierX, maxHeight * multiplierY)
                .requiredSize(imageSize)
        )
    }
}

@Composable
fun HomeBody(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        Text(
            text = "Los servicios que necesitas al alcance de tu mano",
            fontSize = 23.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        Button(modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            onClick = { navController.navigate(route = DomusScreens.Login.route)}
        ) {
            Text(text = "Ingresa", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
        Text("¿No tienes cuenta?", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        ClickableText(text = AnnotatedString("Registrate aqui"),  onClick = {
            navController.navigate(route = DomusScreens.Register.route)
        }, style = TextStyle(color = MaterialTheme.colorScheme.error, fontSize = 18.sp))

    }
}

