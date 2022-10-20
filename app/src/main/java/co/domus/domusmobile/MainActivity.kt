package co.domus.domusmobile

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.domus.domusmobile.ui.DomusTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DomusTheme {
                // A surface container using the 'background' color from the theme
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
                MaterialTheme.colors.primary
            )
    ) {
        Text(
            text = "Te damos la bienvenida a",
            color = Color.White,
            maxLines = 2,
            style = MaterialTheme.typography.h3,
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
                .padding(vertical = maxHeight * 0.15f, horizontal = 40.dp)
        )
        val painter = painterResource(R.drawable.homeimg)
        var multiplierY: Float
        var multiplierX: Float
        var imageSize: Dp
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
fun HomeButton() {
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
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(45.dp)
        ) {
            Text(text = "Ingresa")
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.45f))
        Text("No tienes cuenta?")
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        ClickableText(text = AnnotatedString("Registrate aqui"),  onClick = {
            /*TODO*/
        }, style = TextStyle(color = MaterialTheme.colors.onBackground ))

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DomusTheme {
        Column {
            HomeTitle()
            HomeButton()
        }
    }
}

