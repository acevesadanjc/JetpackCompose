package com.compose.hilt.ui.login.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ScrollingView
import com.compose.hilt.ui.login.ui.dialogs.MyAlertDialog
import com.compose.hilt.ui.login.ui.dialogs.MyConfirmDialog
import com.compose.hilt.ui.login.ui.dialogs.MyCustomDialog
import com.compose.hilt.ui.theme.ComposeHiltTheme


@Composable
fun Father() {

    //ALERT DIALOG
    var showDialog by remember { mutableStateOf(false) }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { showDialog = true }) {
            Text(text = "Alert Dialog")
        }
    }

    MyConfirmDialog(
        showDialog,
        onConfirmButton = { showDialog = false },
        onDismissButton = { showDialog = false}
    )

    /*
    MyCustomDialog(
        showDialog,
        onDismissButton = {}
    )
    */

    /*MyAlertDialog(
        showDialog,
        onDismissRequest = { showDialog = false },
        onConfirmButton = { Log.i("Juan", "Click Alert Dialog") },
        onDismissButton = { showDialog = false }
    )*/

    //MyRangeSlider()
    //MySlider()

    //MyDropMenu()

    /*
     //STATE HOISTING
    var text by rememberSaveable { mutableStateOf("") }
    var radioSelected by remember { mutableStateOf("Juan") }

    MyMultipleRadioButton(name = radioSelected) {
        radioSelected = it
    }
    */

    //SE PASA EL STATE Y SE MODIFICA EN LA LAMBDA
    /*EditTextField(input = text) {
        text = it
    }*/

}

@Composable
fun MySlider() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        var sliderPosition by remember { mutableStateOf(0f) }
        var compleatValue by remember { mutableStateOf("") }
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition = it},
            onValueChangeFinished = { compleatValue = sliderPosition.toString() }, //Se llama cuando el slider se pare para que asignes el valor a otra variable
            valueRange = 0f..10f, //Rango de valores
            steps = 9 // si sueltas el slide te regresa a cada paso
        )
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun MyRangeSlider() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 25.dp)
    ) {
        var currentRange by remember { mutableStateOf(0f..10f) } //Rango actual
        var compleatValue by remember { mutableStateOf("") }
        RangeSlider(
            value = currentRange,
            onValueChange = {currentRange = it},
            //onValueChangeFinished = { compleatValue = currentRange.toString() }, //Se llama cuando el slider se pare para que asignes el valor a otra variable
            valueRange = 0f..10f, //Rango de valores
            steps = 9 // si sueltas el slide te regresa a cada paso
        )
        Text(text = "Valor inferior ${currentRange.start}")
        Text(text = "Valor superior ${currentRange.endInclusive}")
    }
}


@Composable
fun MyDropMenu() {
    var selectedText by remember { mutableStateOf("") }
    //Expander por default false o sea no se muestra
    var expanded by remember { mutableStateOf(false) }
    val desserts: List<String> = listOf("Pay Queso", "Helado", "Pastel Chocolate", "Cafe")

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        OutlinedTextField(
            value = selectedText, //Este sera el valor del item seleccionado
            enabled = false,
            readOnly = true, //solo se puede leer y no escribir en el
            modifier = Modifier
                .clickable {
                    expanded = true //Cuando des click se expande
                    //Celda de la cual sale el dropDownMenu
                }
                .fillMaxWidth(),
            onValueChange = { selectedText = it }
        )
        //Se crea el dropDownMenu
        DropdownMenu(
            expanded = expanded, //Muestras tod0 o no cuando hagas click
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            //Aqui se agregan los Items creando un DropDownItem
            desserts.forEach { dessert ->
                DropdownMenuItem(
                    modifier = Modifier,
                    text = {
                        Text(text = dessert)
                           },
                    onClick = {
                        expanded = false
                        selectedText = dessert //Se asigna el item seleccionado al value del textField
                    }
                )
            }
        }
    }
}

@Composable
fun MyMultipleRadioButton(name: String, onItemSelected: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = name == "Juan", onClick = { onItemSelected("Juan") })
            Text(text = "Juan")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(selected = name == "Samantha", onClick = { onItemSelected("Samantha") })
            Text(text = "Samantha")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = name == "Fabian", onClick = { onItemSelected("Fabian") })
            Text(text = "Fabian")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = name == "Lalito", onClick = { onItemSelected("Lalito") })
            Text(text = "Lalito")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = name == "Santi", onClick = { onItemSelected("Santi") })
            Text(text = "Santi")
        }
    }
}

@Composable
fun EditTextField(
    input: String,
    onValueChanged: (String) -> Unit
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = input,
            onValueChange = {
                onValueChanged(it)
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(20.dp)
    )
}

@Composable
fun SuperText(name: String) {
    ComposeHiltTheme {
        //Por default es wrap_content
        //Los margenes desaparecieron y se sustituyen por padding
        Greeting(
            name = name,
            modifier = Modifier
                .width(50.dp)
                .width(200.dp)
                .fillMaxWidth()//ocupa el ancho completamente match_parent
                .fillMaxHeight()//ocupa la altura completamente match_parent
                .fillMaxSize()//ocupa el ancho y la altura completamente
                .padding(start = 10.dp)
                .padding(end = 10.dp)
                .padding(horizontal = 20.dp)
                .clickable { }
        )
    }
}


@Composable
fun MyBox(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center //Alinea el Box de que esta adentro
    ) {
        //Box Caja azul
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState()), //Scroll
            contentAlignment = Alignment.BottomCenter //Aliniacion del componente dentro del box
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Composable
fun MyColum() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        /*Text(
            text = "Ejemplo 1", modifier = Modifier
                .background(Color.Red)
                .weight(1f) // Se ajusta por peso o porcentaje
        ) */
        Text(
            text = "Ejemplo 1", modifier = Modifier
                .background(Color.Red)
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Ejemplo 2", modifier = Modifier
                .background(Color.Cyan)
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Ejemplo 3", modifier = Modifier
                .background(Color.Magenta)
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Yellow)
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Black)
                .height(200.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun MyRow() {
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Ejemplo 1", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 2", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 3", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 4", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 5", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 6", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        Text(text = "Ejemplo 7", styleFormatText(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
    }
}

//States and rememberSavable
@Composable
fun ButtonStateExample() {
    var counter by rememberSaveable { mutableStateOf(0) }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick ={ counter += 1 }
        ) {
            Text(text = "Pulsar")
        }
        Text(
            text = "Has pulsado: $counter veces",
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                textDecoration = TextDecoration.Underline
            )
        )
        Text(
            text = "Has pulsado: $counter veces",
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            )
        )
    }
}


@Composable
fun MyTextField() {
    var myText by rememberSaveable { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = myText,
            onValueChange = {
                myText = if (it == "a") {
                    it.replace("a", "")
                } else {
                    it
                }
            },
            label = {
                Text("Introduce tu nombre")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutLined() {
    var myText by rememberSaveable { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = myText,
            onValueChange = {
                myText = if (it == "a") {
                    it.replace("a", "")
                } else {
                    it
                }
            },
            label = {
                Text("Introduce tu nombre")
            },
            modifier = Modifier.padding(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.Red,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Magenta
            )
        )
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun styleFormatText(): Modifier = Modifier
    .width(100.dp)
    .background(Color.Green)
    .padding(
        vertical = 20.dp,
        horizontal = 0.dp
    )//marco relleno
    .padding(
        top = 20.dp,
        bottom = 20.dp,
        start = 0.dp,
        end = 0.dp
    )// Relleno por dentro


//PREVIEW CON PARAMETROS
//MODIFICADORES O ATRIBUTOS
@Preview(
    name = "My Preview",
    //heightDp = 50,
    //widthDp = 200,
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL
)
@Composable
fun DefaultPreview() {
    //SuperText(name = "HOMERO")
    //MyBox("Juan Carlos")
    //MyColum()
    //MyRow()
    //ButtonStateExample()
    Father()
}
