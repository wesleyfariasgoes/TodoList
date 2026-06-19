package com.wfg.todolist.ui

sealed interface UiEvent {
    data class ShowSnackbar(val mensage: String) : UiEvent
    data object NavigateBack : UiEvent
    data class Nsvigate< T : Any>(val route: T) : UiEvent
}