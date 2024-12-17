package com.compose.hilt.ui.features.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.compose.hilt.login.data.modelComponents.navigation.Routes


@Composable
fun Screen1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(
            text = "Screen 1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    navController.navigate(Routes.Screen2.route)
                }
        )
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            text = "Screen 2",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    navController.navigate(Routes.Screen3.route)
                }
        )
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            text = "Screen 3",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    navController.navigate(Routes.Screen4.route.plus("/Aceves"))
                }
        )
    }
}

@Composable
fun Screen4(navController: NavHostController, name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(
            text = "Screen 4",
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.Screen5.createRoute(("AndroidDeveloper")))
                }
        )
        Text(text = "I am $name")
    }
}

@Composable
fun Screen5(navController: NavHostController, job: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Screen 5",
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.Screen6.createRoute("32"))
                }
        )
        Text(text = "I work as $job")
    }
}

@Composable
fun Screen6(navController: NavHostController, age: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Screen 6",
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.Screen1.route)
                }
        )
        Text(text = "I am $age old")
    }
}