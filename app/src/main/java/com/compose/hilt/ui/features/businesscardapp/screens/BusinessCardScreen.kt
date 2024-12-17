package com.compose.hilt.ui.features.businesscardapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hilt.R
import com.compose.hilt.ui.theme.ComposeHiltTheme


@Preview(
    name = "My Preview",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2
)
@Composable
fun BusinessCardPreview() {
    ComposeHiltTheme {
        BusinessCardScreen(
            name = "Juan Carlos Aceves Adan",
            career = "Android Developer Extraordinaire",
            telephoneNumber = "5578823432",
            linkedIn = "@juan-carlos-aceves-adan",
            emailAddress = "acevesadanjc@gmail.com"
        )
    }
}


@Composable
fun BusinessCardScreen(
    name: String,
    career: String,
    telephoneNumber: String,
    linkedIn: String,
    emailAddress: String
) {
    Column(
        modifier = Modifier.background(getColor("greenSmall"))
    ) {
        BasicInformationCard(
            name = name,
            career = career,
            modifier = Modifier.weight(1f)
        )
        ContactInformationCard(
            telephoneNumber = telephoneNumber,
            linkedIn = linkedIn,
            emailAddress = emailAddress,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun BasicInformationCard(
    name: String,
    career: String,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 35.dp, start = 25.dp, end = 25.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_logo),
            contentDescription = "Logo de Android",
            modifier = Modifier
                .width(110.dp)
                .height(110.dp)
                .background(getColor("blue"))
        )
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = getColor("grey"),
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = career,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = getColor("green")
        )
    }

}

@Composable
private fun ContactInformationCard(
    telephoneNumber: String,
    linkedIn: String,
    emailAddress: String,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(bottom = 35.dp, start = 25.dp, end = 25.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            UserInformation(
                text = telephoneNumber,
                image = painterResource(id = R.drawable.ic_call),
                modifier = Modifier
            )
            UserInformation(
                text = linkedIn,
                image = painterResource(id = R.drawable.ic_share),
                modifier = Modifier
            )
            UserInformation(
                text = emailAddress,
                image = painterResource(id = R.drawable.ic_email),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun UserInformation(
    text: String,
    image: Painter,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Icon(
            painter = image,
            contentDescription = "Logo de Android",
            tint = getColor("green"),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = getColor("grey"),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

private fun getColor(color: String): Color {
    return when (color) {
        "blue" -> Color(0xFF192540)
        "green" -> Color(0xFF2E7D32)
        "greenSmall" -> Color(0xFFC8E6C9)
        "grey" -> Color(0xFF313131)
        else -> Color(0xFFD0BCFF)
    }
}