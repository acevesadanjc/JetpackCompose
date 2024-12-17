package com.compose.hilt.login.data.modelComponents.navigation

//Sirve para homologar las pantallas y si cambias un id se cambia en tod0 el proyecto
//Aseguramos que no haya un crash
sealed class Routes(val route: String) {

    data object Screen1 : Routes("screen1")
    data object Screen2 : Routes("screen2")
    data object Screen3 : Routes("screen3")
    data object Screen4 : Routes("screen4")
    //obligatorios
    data object Screen5 : Routes("screen5") {
        fun createRoute(job: String) = "screen5/$job"
    }
    //opcionales
    data object Screen6 : Routes("screen6?age={age}") {
        fun createRoute(age: String) = "screen6?age=$age"
    }

}