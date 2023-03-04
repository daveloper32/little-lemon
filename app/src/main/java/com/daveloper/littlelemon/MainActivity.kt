package com.daveloper.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.daveloper.littlelemon.composables.MyNavigation
import com.daveloper.littlelemon.data.db.LittleLemonDatabase
import com.daveloper.littlelemon.data.manager.LittleLemonPreferencesManager
import com.daveloper.littlelemon.data.model.MenuItemEntity
import com.daveloper.littlelemon.data.model.MenuItemNetwork
import com.daveloper.littlelemon.data.model.MenuNetworkData
import com.daveloper.littlelemon.data.model.toMenuItemEntity
import com.daveloper.littlelemon.data.network.NetworkUtils
import com.daveloper.littlelemon.navigation.HomeScreen
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.name
    }

    private lateinit var navController: NavHostController

    private lateinit var littleLemonPreferencesManager: LittleLemonPreferencesManager

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room
            .databaseBuilder(
                applicationContext,
                LittleLemonDatabase::class.java,
                "little_lemon_database"
            ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        littleLemonPreferencesManager = LittleLemonPreferencesManager(this)
        setContent {
            navController = rememberNavController()
            littleLemonPreferencesManager = LittleLemonPreferencesManager(this)
            MainViewComposable(
                navController,
                littleLemonPreferencesManager
            )
        }
        setUpKeyboardViewResize()
        refreshMenuDataFromServer()
    }

    @Composable
    private fun MainViewComposable(
        navController: NavHostController,
        littleLemonPreferencesManager: LittleLemonPreferencesManager
    ) {
        LittleLemonTheme {
            // A surface container using the 'background' color from the theme
            val databaseMenuItems: State<List<MenuItemEntity>?> = database
                .menuItemDao()
                .getAll()
                .observeAsState()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MyNavigation(
                    navHostController = navController,
                    userIsLogged = littleLemonPreferencesManager.userIsLoggedIn(),
                    data = databaseMenuItems,
                    onRegisterSuccess = {
                        refreshMenuDataFromServer()
                    },
                    onLogoutAction = {
                        clearDataFromLocalDB()
                    }
                )
            }
        }
    }

    private fun setUpKeyboardViewResize() {
        ViewCompat
            .setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content)
            ) { view, insets ->
                val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                view.updatePadding(bottom = bottom)
                insets
            }
    }

    private fun refreshMenuDataFromServer() {
        if (littleLemonPreferencesManager.userIsLoggedIn()) {
            lifecycleScope.launch(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    // Get data from network
                    val dataFromNetwork: List<MenuItemNetwork> = fetchMenu()
                    if (dataFromNetwork.isNotEmpty()) {
                        // Save network mapped data in the local DB
                        saveMenuToDatabase(dataFromNetwork)
                    }
                } else {
                    clearDataFromLocalDB()
                    // Get data from network
                    val dataFromNetwork: List<MenuItemNetwork> = fetchMenu()
                    if (dataFromNetwork.isNotEmpty()) {
                        // Save network mapped data in the local DB
                        saveMenuToDatabase(dataFromNetwork)
                    }
                }
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response: MenuNetworkData = httpClient.get(
            NetworkUtils.API_URL
        ).body()
        Log.i(TAG, "fetchMenu() response -> $response")
        return response.menu ?: listOf()
    }

    private fun saveMenuToDatabase(
        menuItemsNetwork: List<MenuItemNetwork>
    ) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemEntity() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    private fun clearDataFromLocalDB() {
        lifecycleScope.launch(Dispatchers.IO) {
            database.menuItemDao().clearTable()
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