package com.compose.hilt.ui.features.birthdaycardapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hilt.R
import com.compose.hilt.ui.theme.ComposeHiltTheme


@Preview(
    name = "My Preview",
    showBackground = true,
)
@Composable
fun BirthdayCardPreview() {
    ComposeHiltTheme {
        GreetingImage(
            message = "Happy Birthday Sam!",
            from = "Aceves",
            modifier = Modifier
        )
    }
}

//Colum is vertical
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp, //Evita el superpuesto del texto, es la distancia entre los textos
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_party_card)
    Box(
        modifier = modifier
    ) {
        Image(
            painter = image,
            contentDescription = null, //descripción para que el talkBack lo lea
            contentScale = ContentScale.Crop, //hace la imagen full screen depende el atributo
            alpha = 0.5F //contraste de la imagen del 0.0 al 1.0
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}




//Row is horizontal
@Composable
fun GreetingTextRow(message: String, from: String, modifier: Modifier = Modifier) {
    Row() {
        Text(
            text = "$message!",
            fontSize = 100.sp,
            lineHeight = 116.sp, //Evita el superpuesto del texto, es lo alto entre los textos
            //modifier = modifier.fillMaxHeight()
        )
        Text(
            text = from, fontSize = 36.sp, modifier = modifier
        )
    }
}
