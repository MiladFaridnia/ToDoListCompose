package com.faridnia.todolistcompose.presentaion.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

typealias HideBackButton = Boolean
typealias ActionBarTitle = String

@Composable
fun NavGraph(
    navController: NavHostController,
    onNavigate: (ActionBarTitle?, HideBackButton) -> Unit,
) {

        NavHost(
            navController = navController,
            startDestination = AUTHENTICATION_ROUTE
        ) {
            loginNavGraph(navController, onNavigate)
        }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}