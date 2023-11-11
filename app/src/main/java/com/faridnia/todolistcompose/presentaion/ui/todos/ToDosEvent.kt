package com.faridnia.todolistcompose.presentaion.ui.todos

sealed class ToDosEvent {
    object OnResetToDosState : ToDosEvent()
    object OnRefreshData : ToDosEvent()

}
