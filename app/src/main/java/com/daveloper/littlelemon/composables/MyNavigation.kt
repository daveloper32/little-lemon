package com.daveloper.littlelemon.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daveloper.littlelemon.navigation.HomeScreen
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.navigation.ProfileScreen

@Composable
fun MyNavigation (
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingScreen.route
    ) {
        composable(OnboardingScreen.route) {
            OnBoarding(navHostController)
        }
        composable(HomeScreen.route) {
            Home(navHostController)
        }
        composable(ProfileScreen.route) {
            Profile(navHostController)
        }
    }
}