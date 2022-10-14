package co.domus.domusmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.domus.domusmobile.ui.Chartreuse
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
                    HomeTitle()
                }
            }
        }
    }
}

@Composable
fun HomeTitle() {
    Surface(color = MaterialTheme.colors.primary) {
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                    withStyle(style = SpanStyle(color = Chartreuse)) {
                        append("Te damos la\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Chartreuse
                        )
                    ) {
                        append("bienvenida a\n")
                    }
                    withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Chartreuse,
                        fontSize = 24.sp
                        )
                    ){
                        append("DOMUS")
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DomusTheme {
        HomeTitle()
    }
}
