package com.faridnia.todolistcompose.presentaion.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faridnia.todolistcompose.R
import com.faridnia.todolistcompose.presentaion.component.CustomButton
import com.faridnia.todolistcompose.presentaion.component.CustomTextInput
import com.faridnia.todolistcompose.presentaion.nav_graph.Screen
import com.faridnia.todolistcompose.util.LightAndDarkPreview
import com.faridnia.todolistcompose.util.handleKeyboard

@LightAndDarkPreview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        state = remember { mutableStateOf(LoginState()) },
        onEvent = {},
        navController = rememberNavController()
    )
}

@Composable
fun LoginScreen(
    state: State<LoginState>,
    onEvent: (LoginEvent) -> Unit,
    navController: NavController
) {

    val userName = rememberSaveable { mutableStateOf("") }

    val scrollState = handleKeyboard()

    if (state.value.isSuccess) {
        userName.value = ""
        onEvent(LoginEvent.OnResetLoginState)
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(Screen.LoginScreen.route)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.d("Milad", "Dispose LoginScreen")
            onEvent(LoginEvent.OnResetLoginState)
        }
    }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(start = 24.dp, end = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(50.dp)
                .height(40.dp),
            painter = painterResource(id = R.drawable.login),
            contentDescription = "To Do",
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .height(32.dp)
                .align(CenterHorizontally),
            text = "Welcome To Do List",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = FontFamily(Font(R.font.raleway_light)),
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.onSurface,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.height(24.dp),
            text = "Login to your account",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.raleway_light)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextInput(
            textValue = userName,
            onTextChanged = {
                userName.value = it
                onEvent(
                    LoginEvent.OnLoginClick(
                        userName.value
                    )
                )
            },
            labelText = "User Name",
            placeHolderText = "",
            supportingText = state.value.error,
            isError = state.value.error.isNotEmpty(),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Continue",
            isEnabled = true,//state.value.error.isNotEmpty(),
            isLoading = state.value.isLoading,
            onClick = {
                onEvent(LoginEvent.OnLoginClick(userName.value))
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

    }
}

