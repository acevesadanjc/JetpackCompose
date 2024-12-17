package com.compose.hilt.ui.features.recyclerview

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hilt.R
import com.compose.hilt.login.data.model.superheroes.SuperHero
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroes() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //SuperHeroVerticalView(context = context)
        //SuperHeroHorizontalView(context = context)
        //SuperHeroVerticalGridView(context = context)
        //SuperHeroHorizontalGridView(context = context)
        //SuperHeroRememberStatesView(context = context)
        SuperHeroStickyView(context = context)
    }
}

//Sticky Headers
@ExperimentalFoundationApi
@Composable
fun SuperHeroStickyView(context: Context) {
    //listado de objetos que su modelo de datos sea ese tipo de publisher dc todos los de dc
    val sticky: Map<String, List<SuperHero>> = getSuperHeroes().groupBy {
        it.publisher
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp) //espacio entre items
    ) {
        sticky.forEach { (publisher , superHeroModel) ->
            stickyHeader {
                Text(
                    text = publisher,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().background(Color.Black)
                )
            }
            items(superHeroModel) { hero ->
                ItemHero(superHero = hero) {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


//Reciclador Vertical
@Composable
fun SuperHeroVerticalView(context: Context) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp) //espacio entre items
    ) {
        items(getSuperHeroes()) { hero ->
            ItemHero(superHero = hero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//Reciclador Horizontal
@Composable
fun SuperHeroHorizontalView(context: Context) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) //espacio entre items
    ) {
        items(getSuperHeroes()) { hero ->
            ItemHero(superHero = hero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//Reciclador en MAYA VERTICAL GRID 2 columnas verticales y las demás son filas
@Composable
fun SuperHeroVerticalGridView(context: Context) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(getSuperHeroes()) { hero ->
            ItemHero(superHero = hero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//Reciclador en MAYA HORIZONTAL GRID 2 filas y las demás son columnas
@Composable
fun SuperHeroHorizontalGridView(context: Context) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(getSuperHeroes()) { hero ->
            ItemHero(superHero = hero) {
                Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//Recordar estados
@Composable
fun SuperHeroRememberStatesView(context: Context) {
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    //Controlador de estados
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHeroes()) { hero ->
                ItemHero(superHero = hero) {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        if (showButton) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "I am a Button")
            }
        }
    }
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier.width(200.dp),
        onClick = { onItemSelected(superHero) }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = superHero.superHeroName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = superHero.superHeroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 15.dp, end = 10.dp)
            )
        }
    }
}

private fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Petter Parker ", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginPreview() {
    SuperHeroes()
}