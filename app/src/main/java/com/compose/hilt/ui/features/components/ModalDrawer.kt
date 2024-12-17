package com.compose.hilt.ui.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// https://github.com/android/snippets/blob/f595a0d317fd8aca6b33d4344bf87696cd45c481/compose/snippets/src/main/java/com/example/compose/snippets/layouts/MaterialLayoutSnippets.kt#L328-L356

//Se le pasa una lista
@Composable
fun MenuDrawerView() {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text( text = "Primera Opción", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        Text( text = "Segunta Opción", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        Text( text = "Tercera Opción", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        Text( text = "Cuarta Opción", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
    }
}


