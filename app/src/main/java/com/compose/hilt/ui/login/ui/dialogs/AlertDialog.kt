package com.compose.hilt.ui.login.ui.dialogs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.compose.hilt.R
import com.compose.hilt.ui.theme.white


//CONFIRM DIALOG
@Composable
fun MyConfirmDialog(
    show: Boolean,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismissButton() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(containerColor = white),
                    modifier = Modifier.padding(24.dp)
                ) {
                    TitleDialog("Phone Ringtone", modifier = Modifier.padding(24.dp))
                    Divider(Modifier.fillMaxWidth(), color = Color.Gray)
                    var radioSelected by remember { mutableStateOf("") }
                    MyMultipleRadioButton(radioSelected) { radioSelected = it }
                    Row(
                        Modifier
                            .align(Alignment.End)
                            .padding(8.dp)
                    ) {
                        TextButton(onClick = { onDismissButton() }) {
                            Text(text = "CANCEL")
                        }
                        TextButton(onClick = { onConfirmButton() }) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}

//CUSTOM DIALOG
@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismissButton: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismissButton() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    TitleDialog("Inicia Sesión")
                    AccountItem("acevesadanjc@gmail.com", R.drawable.avatar) {

                    }
                    AccountItem("samanthaysoto@gmail.com", R.drawable.avatar) {

                    }
                    AccountItem("Añadir", R.drawable.add) {

                    }
                }
            }
        }
    }
}

@Composable
fun TitleDialog(title: String, modifier: Modifier = Modifier.padding(bottom = 12.dp)) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
fun AccountItem(
    email: String,
    @DrawableRes drawable: Int,
    onClickAction: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClickAction() }
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MyMultipleRadioButton(name: String, onItemSelected: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(selected = name == "Juan", onClick = { onItemSelected("Juan") })
            Text(text = "Juan")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(selected = name == "Samantha", onClick = { onItemSelected("Samantha") })
            Text(text = "Samantha")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(selected = name == "Fabian", onClick = { onItemSelected("Fabian") })
            Text(text = "Fabian")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(selected = name == "Lalito", onClick = { onItemSelected("Lalito") })
            Text(text = "Lalito")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(selected = name == "Santi", onClick = { onItemSelected("Santi") })
            Text(text = "Santi")
        }
    }
}


//NORMAL DIALOG
@Composable
fun MyAlertDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Descripción de mi dialog") },
            confirmButton = {
                TextButton(onClick = { onConfirmButton() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissButton() }) {
                    Text(text = "Cancelar")
                }
            },
            modifier = Modifier
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun AlertDialogPreview() {
    MyConfirmDialog(true, onConfirmButton = {}, onDismissButton = { })
    //MyCustomDialog(show = true, onDismissButton = { })
    //MyAlertDialog(show = true, onDismissRequest = {}, onConfirmButton = {}, onDismissButton = {})
}