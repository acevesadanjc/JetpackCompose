package com.compose.hilt

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.compose.hilt.ui.features.artspace.ArtSpaceActivity
import com.compose.hilt.ui.features.lemonapp.LemonadeActivity
import com.compose.hilt.ui.features.tipcalculatorap.TipCalculatorActivity
import com.compose.hilt.login.ui.activity.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
    }
}