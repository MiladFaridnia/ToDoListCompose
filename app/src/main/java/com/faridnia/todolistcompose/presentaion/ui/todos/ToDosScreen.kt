package com.faridnia.todolistcompose.presentaion.ui.todos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faridnia.todolistcompose.R
import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoDtoItem
import com.faridnia.todolistcompose.util.LightAndDarkPreview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@LightAndDarkPreview
@Composable
fun PreviewToDosScreen() {
    ToDosScreen(
        state = remember { mutableStateOf(TodosState(toDoList = getSample())) },
        onEvent = {},
        navController = rememberNavController()
    )
}

@Composable
fun ToDosScreen(
    state: State<TodosState>,
    onEvent: (ToDosEvent) -> Unit,
    navController: NavController
) {

    val userName = rememberSaveable { mutableStateOf("") }

/*    DisposableEffect(Unit) {
        onDispose {
            Log.d("Milad", "Dispose LoginScreen")
            onEvent(ToDosEvent.OnResetToDosState)
        }
    }*/

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(start = 24.dp, end = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            modifier = Modifier
                .padding(1.dp)
                .width(50.dp)
                .height(40.dp),
            painter = painterResource(id = R.drawable.todo_list),
            contentDescription = "To Do",
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .height(32.dp)
                .align(CenterHorizontally),
            text = "To Do List",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = FontFamily(Font(R.font.raleway_light)),
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.onSurface,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        val swipeRefreshState = rememberSwipeRefreshState(
            isRefreshing = state.value.isLoading
        )

        SwipeRefresh(state = swipeRefreshState,
            onRefresh = {
                onEvent(ToDosEvent.OnRefreshData)
            }
        ) {
            LazyColumn(
                modifier = Modifier.weight(1.0f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.value.toDoList) {
                    ToDoItem(
                        itemName = it.title,
                        isChecked = it.completed == true,
                        onClick = { }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

    }
}

fun getSample(): List<ToDoDtoItem> {
    return listOf(
        ToDoDtoItem(completed = true, id = 1, title = "title 1", userId = 10000),
        ToDoDtoItem(completed = false, id = 1, title = "title 2", userId = 10000),
        ToDoDtoItem(completed = true, id = 1, title = "title 3", userId = 10000),
        ToDoDtoItem(completed = false, id = 1, title = "title 4", userId = 10000),
        ToDoDtoItem(completed = true, id = 1, title = "title 5", userId = 10000)
    )
}

