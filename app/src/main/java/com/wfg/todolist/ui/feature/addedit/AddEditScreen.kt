package com.wfg.todolist.ui.feature.addedit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wfg.todolist.R
import com.wfg.todolist.data.TodoDatabaseProvider
import com.wfg.todolist.data.TodoRepositoryImpl
import com.wfg.todolist.ui.UiEvent
import com.wfg.todolist.ui.components.AppToolbar
import com.wfg.todolist.ui.theme.TodoListTheme

@Composable
fun AddEditScreen(
    id: Long? = null,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.todoDao
    )
    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(
            id = id,
            repository = repository
        )
    }
    val title = viewModel.title
    val description = viewModel.description

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(it.mensage)
                }
                is UiEvent.NavigateBack -> {
                    navigateBack()
                }
                is UiEvent.Nsvigate<*> -> {}
            }
        }
    }

    AddEditContent(
        title = title,
        description = description,
        snackbarHostState = snackbarHostState,
        onNavigateBack = navigateBack,
        onEvent = viewModel::onEvent)
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {
    AppToolbar(
        title = stringResource(R.string.add_edit_screen_title),
        showBackButton = true,
        onBackClick = { onNavigateBack() },
        actions = {
            IconButton(onClick = { /* Favoritar */ }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = stringResource(R.string.add_edit_favorite_description),
                    tint = colorResource(id = R.color.white)
                )
            }
        }
    ) { paddingValues ->
        //Esse Box faz necessário para que o scaffold não ocupe toda a fique abaixo da toolbar
        Box(modifier = Modifier.padding(paddingValues)) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        onEvent(AddEditEvent.Save)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.add_edit_save_description)
                        )
                    }
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .consumeWindowInsets(padding)
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = {
                            onEvent(AddEditEvent.TitleChanged(it))
                        },
                        placeholder = { Text(stringResource(R.string.add_edit_title_placeholder)) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = description ?: "",
                        onValueChange = {
                            onEvent(AddEditEvent.DescriptionChanged(it))
                        },
                        placeholder = { Text(stringResource(R.string.add_edit_description_placeholder)) }
                    )

                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AddeditScreenPreview() {
    TodoListTheme() {
        AddEditContent(onNavigateBack = {}, onEvent = {}, title = "", description = null, snackbarHostState = SnackbarHostState())
    }
}