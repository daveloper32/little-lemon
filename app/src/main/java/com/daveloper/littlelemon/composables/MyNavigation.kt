package com.daveloper.littlelemon.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daveloper.littlelemon.data.model.MenuItemEntity
import com.daveloper.littlelemon.navigation.HomeScreen
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.navigation.ProfileScreen

@Composable
fun MyNavigation (
    navHostController: NavHostController,
    userIsLogged: Boolean = false,
    data: State<List<MenuItemEntity>?>,
    onRegisterSuccess: () -> Unit,
    onLogoutAction: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = if (userIsLogged) { HomeScreen.route } else { OnboardingScreen.route }
    ) {
        composable(OnboardingScreen.route) {
            OnBoarding(
                navHostController,
                onRegisterSuccess
            )
        }
        composable(HomeScreen.route) {
            Home(
                navHostController,
                data
            )
        }
        composable(ProfileScreen.route) {
            Profile(
                navHostController,
                onLogoutAction
            )
        }
    }
}