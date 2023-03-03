package com.daveloper.littlelemon.navigation

interface Destinations {
    val route: String
}

object OnboardingScreen: Destinations {
    override val route: String = "onboarding"
}

object HomeScreen: Destinations {
    override val route: String = "home"
}

object ProfileScreen: Destinations {
    override val route: String = "profile"
}