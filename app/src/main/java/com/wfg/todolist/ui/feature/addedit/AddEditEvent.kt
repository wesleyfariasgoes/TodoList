package com.wfg.todolist.ui.feature.addedit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String) : AddEditEvent
    data class DescriptionChanged(val description: String) : AddEditEvent
    object Save : AddEditEvent
}