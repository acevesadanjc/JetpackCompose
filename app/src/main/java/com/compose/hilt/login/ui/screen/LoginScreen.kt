package com.compose.hilt.login.ui.screen

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.compose.hilt.R
import com.compose.hilt.login.ui.interfaces.SuperHeroesUiState
import com.compose.hilt.login.ui.viewModel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val activity = LocalContext.current as Activity
        val isProgress by loginViewModel.isProgress.observeAsState(initial = false)
        val isSuccess by loginViewModel.isSuccess.observeAsState(initial = false)
        val isError by loginViewModel.isError.observeAsState(initial = false)

        //FLOW
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val uiState by produceState<SuperHeroesUiState>(
            initialValue = SuperHeroesUiState.Loading,
            key1 = lifecycle,
            key2 = loginViewModel
        ) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                loginViewModel.uiState.collect { value = it } //Recuperar valores, es el UIstate
            }
        }

        when(uiState) {
            is SuperHeroesUiState.Error -> {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
            SuperHeroesUiState.Loading -> {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is SuperHeroesUiState.Success -> {
                //El align setearse dentro del layout padre directo (Header) y pasar el modificador align al componente en este caso (Icon)
                Header(Modifier.align(Alignment.TopEnd))
                Body(loginViewModel = loginViewModel, modifier = Modifier.align(Alignment.Center))
                Footer(Modifier.align(Alignment.BottomCenter))
            }
        }
        /*
        if (isProgress) {
           Row(
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.fillMaxSize()
           ) {
               CircularProgressIndicator()
           }
        } else {
            //El align setearse dentro del layout padre directo (Header) y pasar el modificador align al componente en este caso (Icon)
            Header(Modifier.align(Alignment.TopEnd))
            Body(loginViewModel = loginViewModel, modifier = Modifier.align(Alignment.Center))
            Footer(Modifier.align(Alignment.BottomCenter))
        }
        */

        if (isSuccess) {
            Toast.makeText(activity, "Operacion Exitosa =)", Toast.LENGTH_SHORT).show()
        }
        if (isError) {
            Toast.makeText(activity, "Operacion Fallida!", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Header(modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier.clickable {
            //activity.finish()
        }
    )
}

@Composable
fun Body(loginViewModel: LoginViewModel, modifier: Modifier) {
    //Estará escuchando ese valor del viewModel
    val emailInput by loginViewModel.email.observeAsState(initial = "")
    val passwordInput by loginViewModel.pass.observeAsState(initial = "")
    val isEnabledBtn by loginViewModel.isEnabledBtn.observeAsState(initial = false)


    Column(modifier = modifier) {

        ImageLogo(modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(24)

        EmailTextField(
            label = R.string.email_input,
            leadingIcon = null,
            inputText = emailInput,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onChangedValue = {
                loginViewModel.onParamsChanged(email = it, password = passwordInput)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(8)

        PasswordTextField(
            label = R.string.password_input,
            leadingIcon = null,
            inputText = passwordInput,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            onChangedValue = {
                loginViewModel.onParamsChanged(email = emailInput, password = it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(16)

        //Para alinear un componente necesitamos el modificador del padre directo
        ForgotPasswordText(
            R.string.forgot_password,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(24)

        LoginButton(
            isEnabledBtn = isEnabledBtn,
            text = R.string.log_in,
            onClickResult = {
                loginViewModel.login(username = emailInput, password = passwordInput)
            },
            modifier = Modifier
        )

        Spacer(16)

        LoginDivider(
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(32)

        SocialLogin(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo Instagram",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    @StringRes label: Int, //To denote that the label parameter is expected to be a string resource reference, use @StringRes annotation
    @DrawableRes leadingIcon: Int?,
    inputText: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    onChangedValue: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = inputText,//This is the state of the app for the bill amount
        onValueChange = { onChangedValue(it) }, //It is the result of onValueChange
        singleLine = true, //una sola linea
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFAFAFA),
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            if (leadingIcon != null)
                Icon(painter = painterResource(id = leadingIcon), null)
        },
        placeholder = {
            Text(text = stringResource(id = label))
        }
        /*label = {
            Text(text = stringResource(id = label))
        }*/
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    @StringRes label: Int, //To denote that the label parameter is expected to be a string resource reference, use @StringRes annotation
    @DrawableRes leadingIcon: Int?,
    inputText: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    onChangedValue: (String) -> Unit
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = modifier,
        value = inputText,//This is the state of the app for the bill amount
        onValueChange = { onChangedValue(it) }, //It is the result of onValueChange
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFAFAFA),
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            if (leadingIcon != null)
                Icon(painter = painterResource(id = leadingIcon), null)
        },
        trailingIcon = {
            val icon = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Show password"
                )
            }
        },
        //se oculta o se hace visible la contraseña visualTransformation
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        placeholder = {
            Text(text = stringResource(id = label))
        }
        /*label = {
            Text(text = stringResource(id = label))
        }*/
    )
}

@Composable
fun ForgotPasswordText(
    @StringRes text: Int,
    modifier: Modifier
) {
    Text(
        text = stringResource(id = text),
        color = Color(0xFF4EA8E9),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun LoginButton(
    isEnabledBtn: Boolean,
    @StringRes text: Int,
    onClickResult: () -> Unit,
    modifier: Modifier
) {
    Button(
        enabled = isEnabledBtn,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9), //Color de fondo botón habilitado
            disabledContainerColor = Color(0xFF78C8F9), //Color de fondo botón deshabilitado
            contentColor = Color.White,//Color de texto botón habilitado
            disabledContentColor = Color.White //Color de texto botón deshabilitado
        ),
        onClick = { onClickResult() }
    ) {
        Text(text = stringResource(id = text))
    }
}

@Composable
fun LoginDivider(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.or),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5),
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        HorizontalDivider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun SocialLogin(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Facebook icon",
            modifier = Modifier
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.social_label),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(24)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.footer_label),
                fontSize = 12.sp,
                color = Color(0xFFB5B5B5),
                modifier = Modifier
            )
            Text(
                text = stringResource(id = R.string.sign_up),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4EA8E9),
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Spacer(32)
    }
}


@Composable
fun Spacer(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}


private fun validateLoginSetBtn(email: String, password: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginPreview() {
    //LoginScreen()
}