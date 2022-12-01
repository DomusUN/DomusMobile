package co.domus.domusmobile.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.domus.domusmobile.R
import co.domus.domusmobile.model.Service
import co.domus.domusmobile.navigation.DomusScreens

@SuppressLint("DiscouragedApi")
@Composable
fun ServiceItem(service: Service, context: Context, navController: NavController) {
    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 5. dp
    )
    Card(
        modifier = Modifier
            .padding(20.dp, 10.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = customCardElevation,
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.primary,
        ),
    ) {
        Column(modifier = Modifier.clickable(onClick = {
            navController.navigate(route = DomusScreens.WorkersList.route + "/" + service.service_id)
        }), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            var painter = context.resources.getIdentifier(
                service.image_name,
                "drawable",
                context.packageName
            )
            if(painter == 0){
                painter = R.drawable.logo_plomeria
            }
            Image(
                painter = painterResource(painter),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    service.name,
                    Modifier.padding(10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}