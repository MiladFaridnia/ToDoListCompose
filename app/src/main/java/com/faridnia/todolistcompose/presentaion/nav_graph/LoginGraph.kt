package com.faridnia.todolistcompose.presentaion.nav_graph

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.faridnia.todolistcompose.presentaion.ui.login.LoginScreen
import com.faridnia.todolistcompose.presentaion.ui.login.LoginViewModel
import com.faridnia.todolistcompose.presentaion.ui.todos.ToDosScreen
import com.faridnia.todolistcompose.presentaion.ui.todos.ToDosViewModel

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    onNavigate: (ActionBarTitle?, HideBackButton) -> Unit,
) {
    navigation(startDestination = Screen.LoginScreen.route, route = AUTHENTICATION_ROUTE) {

        composable(route = Screen.LoginScreen.route) {
            val viewModel: LoginViewModel = hiltViewModel()

            val state = viewModel.state.collectAsState()
            LoginScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navController = navController
            )
            onNavigate(Screen.LoginScreen.title, false)
        }

        composable(route = Screen.ToDosScreen.route) {
            val viewModel: ToDosViewModel = hiltViewModel()

            val state = viewModel.state.collectAsState()
            ToDosScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navController = navController
            )
            onNavigate(Screen.ToDosScreen.title, true)
        }

    }
}