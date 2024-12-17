package com.compose.hilt.login.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.hilt.login.data.modelComponents.navigation.Routes
import com.compose.hilt.login.ui.viewModel.LoginViewModel
import com.compose.hilt.login.ui.screen.LoginScreen
import com.compose.hilt.ui.features.components.navigation.Screen1
import com.compose.hilt.ui.features.components.navigation.Screen2
import com.compose.hilt.ui.features.components.navigation.Screen3
import com.compose.hilt.ui.features.components.navigation.Screen4
import com.compose.hilt.ui.features.components.navigation.Screen5
import com.compose.hilt.ui.features.components.navigation.Screen6

import com.compose.hilt.ui.theme.ComposeHiltTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Set content is a function that is used to define a layout in your app using Composable functions.
        setContent {
            ComposeHiltTheme {
                //Surface is a Composable that has a background color and can contain other Composables.
                Surface(
                    modifier = Modifier.fillMaxSize(), //el fondo abarca t0do el ancho y el alto
                    color = MaterialTheme.colorScheme.background
                ) {
                    //inyectar la dependencia del viewModel eso es lo correcto

                    LoginScreen(loginViewModel)

                    /*
                    //navigationController sirve para navegar
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Screen1.route) {
                        //el destination te da la ruta
                        composable(Routes.Screen1.route) { Screen1(navigationController) }
                        composable(Routes.Screen2.route) { Screen2(navigationController) }
                        composable(Routes.Screen3.route) { Screen3(navigationController) }
                        //Campos obligatorios
                        composable(Routes.Screen4.route.plus("/{name}")) { backStackEntry ->
                            Screen4(
                                navController = navigationController,
                                name = backStackEntry.arguments?.getString("name").orEmpty()
                            )
                        }
                        //Campos obligatorios
                        composable(
                            Routes.Screen5.route.plus("/{job}"),
                            arguments = listOf(navArgument("job") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            Screen5(
                                navController = navigationController,
                                job = backStackEntry.arguments?.getString("job").orEmpty()
                            )
                        }

                        //Campos opcionales
                        composable(
                            Routes.Screen6.route,
                            arguments = listOf(navArgument("age") { defaultValue = ""})
                        ) { backStackEntry ->
                            Screen6(
                                navController = navigationController,
                                age = backStackEntry.arguments?.getString("age").orEmpty()
                            )
                        }
                    }
                    */


                    //ScaffoldExample()
                    //SuperHeroes()
                    //Father()
                    /*
                    GreetingImage(
                        message = stringResource(id = R.string.happy_birthday_text),
                        from = stringResource(R.string.signature_text)
                    )*/
                    /*
                    ComposeArticleAppScreen(
                        title = stringResource(R.string.title_jetpack_compose_text),
                        shortDescription = stringResource(R.string.description_jetpack_compose_text),
                        longDescription = stringResource(R.string.content_jetpack_compose_text),
                        painterResource(id = R.drawable.bg_compose_background)
                    )*/
                    /*
                    TaskCompletedScreen(
                        title = stringResource(R.string.title_tasks_completed_text),
                        description = stringResource(R.string.description_nice_work_text),
                    )*/
                    /*
                    ComposableFunctionsInfoView(
                        getTexts()
                    )*/
                    /*BusinessCardScreen(
                        stringResource(R.string.full_name_business_card_text),
                        stringResource(R.string.career_business_card_text),
                        stringResource(R.string.phone_number_business_card_text),
                        stringResource(R.string.social_media_business_card_text),
                        stringResource(R.string.email_address_number_business_card_text)
                    )*/

                    //DiceRollerAppPreview()

                    //ButtonStateExample()
                    //MyTextField()
                    //MyTextFieldOutLined()
                }
            }
        }

        /*binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }*/
    }


    private fun getTexts(): MutableMap<String, String> {
        val textsMap = mutableMapOf<String, String>()
        textsMap["txtTitle"] = "Text composable"
        textsMap["txtDesc"] = "Displays text and follows the recommended Material Design guidelines."
        textsMap["imgTitle"] = "Image composable"
        textsMap["imgDesc"] =
            "Creates a composable that lays out and draws a given Painter class object."
        textsMap["rowTitle"] = "Row composable"
        textsMap["rowDesc"] = "A layout composable that places its children in a horizontal sequence."
        textsMap["columTitle"] = "Column composable"
        textsMap["columDesc"] = "A layout composable that places its children in a vertical sequence."
        return textsMap
    }

    /* private fun updateUiWithUser(model: LoggedInUserView) {
         val welcome = getString(R.string.welcome)
         val displayName = model.displayName
         // TODO : initiate successful logged in experience
         Toast.makeText(
             applicationContext,
             "$welcome $displayName",
             Toast.LENGTH_LONG
         ).show()
     }

     private fun showLoginFailed(@StringRes errorString: Int) {
         Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
     }*/
}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
/*
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}*/