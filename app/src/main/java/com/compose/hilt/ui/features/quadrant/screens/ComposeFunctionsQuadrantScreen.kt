package com.compose.hilt.ui.features.quadrant.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.hilt.ui.theme.ComposeHiltTheme

@Preview(
    name = "My Preview",
    showBackground = true,
    device = Devices.PIXEL_2
)
@Composable
fun ComposableFunctionsInfoPreview() {
    ComposeHiltTheme {
        ComposableFunctionsInfoView(getTexts())
    }
}

@Composable
fun ComposableFunctionsInfoView(texts: MutableMap<String, String>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.weight(1f)) {
            ComposableInfoCard(
                title = texts["txtTitle"].toString(),
                description = texts["txtDesc"].toString(),
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = texts["rowTitle"].toString(),
                description = texts["rowDesc"].toString(),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ComposableInfoCard(
                title = texts["imgTitle"].toString(),
                description = texts["imgDesc"].toString(),
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = texts["columTitle"].toString(),
                description = texts["columDesc"].toString(),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f) //Se le pasa el peso por cada elemento
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}


private fun getTexts(): MutableMap<String, String> {
    val textsMap = mutableMapOf<String, String>()
    textsMap["txtTitle"] = "Text composable"
    textsMap["txtDesc"] = "Displays text and follows the recommended Material Design guidelines."
    textsMap["imgTitle"] = "Image composable"
    textsMap["imgDesc"] =
        "Creates a composable that lays out and draws a given Painter class object."
    textsMap["rowTitle"] = "Row composable"
    textsMap["rowDesc"] = "A layout composable that places its children in a horizontal sequence."
    textsMap["columTitle"] = "Column composable"
    textsMap["columDesc"] = "A layout composable that places its children in a vertical sequence."
    return textsMap
}