package com.compose.hilt.ui.features.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


/**
 * Floating Action Button
 *
 */
@Composable
fun FloatingActionButtonView() {
    var indexState by remember { mutableStateOf(0) }

    FloatingActionButton(
        onClick = {},
        backgroundColor = Color.Yellow,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = "Add"
        )
    }
}