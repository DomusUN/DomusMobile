package co.domus.domusmobile

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
                    Column{
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                MaterialTheme.colors.primary
            )
    ) {
        Text(
            text = "Te damos la bienvenida a",
            color = MaterialTheme.colors.onSurface,
            maxLines = 3,
            fontSize = 35.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 40.dp)
        )
        Text(
            text = "Domus",
            color = MaterialTheme.colors.onSurface,
            maxLines = 3,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = 40.dp, horizontal = 40.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.homeimg),
            contentDescription = "",
            modifier = Modifier.align(Alignment.BottomEnd).padding(top = 100.dp)
        )
    }
}

@Composable
fun HomeButton() {
    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .padding(horizontal = 50.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                MaterialTheme.colors.onSurface
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Los servicios que necesitas al alcance de tu mano")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Ingresa")
        }
        Text("No tienes cuenta?")
        Text("Registrate aqui")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DomusTheme {
        Column{
            HomeTitle()
            HomeButton()
        }
    }
}

