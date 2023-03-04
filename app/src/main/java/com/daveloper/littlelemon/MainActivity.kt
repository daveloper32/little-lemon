package com.daveloper.littlelemon

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.daveloper.littlelemon.composables.MyNavigation
import com.daveloper.littlelemon.composables.OnBoarding
import com.daveloper.littlelemon.data.manager.LittleLemonPreferencesManager
import com.daveloper.littlelemon.navigation.HomeScreen
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private lateinit var littleLemonPreferencesManager: LittleLemonPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                littleLemonPreferencesManager = LittleLemonPreferencesManager(this)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavigation(
                        navHostController = navController,
                        userIsLogged = littleLemonPreferencesManager.userIsLoggedIn()
                    )
                }
            }
        }
        ViewCompat
            .setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content)
            ) { view, insets ->
                val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                view.updatePadding(bottom = bottom)
                insets
            }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.route) {
            OnboardingScreen.route, HomeScreen.route -> finish()
            else -> super.onBackPressed()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}