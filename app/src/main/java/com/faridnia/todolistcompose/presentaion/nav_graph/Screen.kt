package com.faridnia.todolistcompose.presentaion.nav_graph


const val AUTHENTICATION_ROUTE = "authentication_route"

sealed class Screen(val route: String, val title: String?) {

    object LoginScreen : Screen(route = "login_screen", title = "Login")

}
