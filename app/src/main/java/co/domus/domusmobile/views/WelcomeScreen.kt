package co.domus.domusmobile.views

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import co.domus.domusmobile.R
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(
                MaterialTheme.colors.primary
            )
    ) {
        Text(
            text = "Te damos la bienvenida a",
            color = Color.White,
            maxLines = 3,
            fontSize = 50.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 45.dp, vertical = 40.dp)
        )
        Text(
            text = "DOMUS",
            color = Color.White,
            maxLines = 3,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = 60.dp, horizontal = 40.dp)
        )
        val painter = painterResource(R.drawable.homeimg)
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(45.dp, 70.dp)
                .requiredSize(250.dp)
        )
    }
}

@Composable
fun WelcomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            HomeTitle()
            HomeButton()
        }
    }
}

@Composable
fun HomeButton() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(horizontal = 50.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                MaterialTheme.colors.onSurface
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Los servicios que necesitas al alcance de tu mano",
            fontSize = 23.sp,
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(45.dp)) {
            Text(text = "Ingresa")
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.45f))
        Text("No tienes cuenta?")
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        ClickableText(text = AnnotatedString("Registrate aqui"), onClick = {
            /*TODO*/
        })
    }
}