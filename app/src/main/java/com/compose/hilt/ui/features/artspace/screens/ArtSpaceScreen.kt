package com.compose.hilt.ui.features.artspace.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hilt.R
import com.compose.hilt.ui.features.lemonapp.model.ResourcesArtSpace
import com.compose.hilt.ui.theme.PurpleC0
import com.compose.hilt.ui.theme.white

@Preview()
@Composable
fun ArtSpacePreview() {
    ArtSpaceMain()
}

@Composable
fun ArtSpaceMain() {
    var stepState by remember { mutableStateOf(0) }
    var imageState by remember { mutableStateOf(R.drawable.el_grito_art) }
    var titleState by remember { mutableStateOf(R.string.el_grito_art) }
    var descState by remember { mutableStateOf(R.string.el_grito_desc_art) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        ArtCardImage(
            image = imageState,
            modifier = Modifier
                .height(450.dp)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InformationText(
                title = titleState,
                description = descState,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                GeneralButton(
                    name = R.string.previous_art,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .padding(start = 8.dp, end = 16.dp),
                    onStartClicked = {
                        if (stepState in 1..5) {
                            stepState--
                            setResources(step = stepState) {
                                imageState = it.image
                                titleState = it.title
                                descState = it.description
                            }
                        }
                    }
                )
                GeneralButton(
                    name = R.string.next_art,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .padding(start = 16.dp, end = 8.dp)
                        .fillMaxWidth(),
                    onStartClicked = {
                        if (stepState in 0..4) {
                            stepState++
                            setResources(step = stepState) {
                                imageState = it.image
                                titleState = it.title
                                descState = it.description
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ArtCardImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(containerColor = white),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.9F,
            modifier = modifier
        )
    }
}

@Composable
fun InformationText(
    @StringRes title: Int,
    @StringRes description: Int,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(containerColor = PurpleC0),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = title),
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp,
            fontWeight = FontWeight.Thin,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        Text(
            text = stringResource(id = description),
            fontFamily = FontFamily.SansSerif,
            fontWeight = fontWeight,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun GeneralButton(
    @StringRes name: Int,
    modifier: Modifier = Modifier,
    onStartClicked: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Button(
            onClick = { onStartClicked() },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(name),
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp
            )
        }
    }
}


fun setResources(step: Int, resources: (ResourcesArtSpace) -> Unit) {
    when (step) {
        0 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.el_grito_art,
                    R.string.el_grito_art,
                    R.string.el_grito_desc_art
                )
            )
        }

        1 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.el_nacimiento_de_venus_art,
                    R.string.el_nacimiento_venus_art,
                    R.string.el_nacimiento_venus_desc_art
                )
            )
        }

        2 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.mona_lisa_art,
                    R.string.la_mona_lisa_art,
                    R.string.la_mona_lisa_desc_art
                )
            )
        }

        3 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.la_noche_estrellada_art,
                    R.string.la_noche_estrellada_art,
                    R.string.la_noche_estrellada_desc_art
                )
            )
        }

        4 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.maja_desnuda_art,
                    R.string.la_maja_desnuda_art,
                    R.string.la_maja_desnuda_desc_art
                )
            )
        }

        5 -> {
            resources(
                ResourcesArtSpace(
                    0,
                    R.drawable.saturno_art,
                    R.string.saturno_art,
                    R.string.saturno_desc_art
                )
            )
        }
    }
}


