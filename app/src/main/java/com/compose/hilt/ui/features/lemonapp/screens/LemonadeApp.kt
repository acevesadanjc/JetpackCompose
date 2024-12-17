package com.compose.hilt.ui.features.lemonapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hilt.R
import java.io.Serializable


@Preview(
    name = "My Preview",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2
)
@Composable
private fun LemonadeAppPreview() {
    LemonadeApp()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    //STATE HOISTING
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->

        when (currentStep) {
            1 -> {
                LemonadeSteps(
                    image = R.drawable.lemon_tree,
                    contentDescription = stringResource(id = R.string.lemon_tree_content_description_text),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonadeSteps(
                    image = R.drawable.lemon_squeeze,
                    contentDescription = stringResource(id = R.string.lemon_squeeze_content_description_text),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeSteps(
                    image = R.drawable.lemon_drink,
                    contentDescription = stringResource(id = R.string.glass_of_lemonade_content_description_text),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonadeSteps(
                    image = R.drawable.lemon_restart,
                    contentDescription = stringResource(id = R.string.empty_glass_content_description_text),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonadeSteps(
    image: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8E6C9)),
                border = BorderStroke(2.dp, Color(0xFF69CDD8)),
                onClick = onClick
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = contentDescription,
                    modifier = Modifier
                )
            }

            MyVerticalSpacer(size = 16)

            Text(
                text = contentDescription,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun MyVerticalSpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}