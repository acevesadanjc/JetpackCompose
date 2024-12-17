package com.compose.hilt.ui.features.lemonapp.diceRoller

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.compose.hilt.ui.features.lemonapp.diceRoller.screens.DiceWithButtonAndImage
import com.compose.hilt.ui.theme.ComposeHiltTheme


class DiceRollerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHiltTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), //el fondo abarca t0do el ancho y el alto
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceWithButtonAndImage()
                }
            }
        }

    }
}