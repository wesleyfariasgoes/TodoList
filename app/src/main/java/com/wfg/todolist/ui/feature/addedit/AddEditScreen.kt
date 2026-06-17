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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wfg.todolist.R
import com.wfg.todolist.ui.components.AppToolbar
import com.wfg.todolist.ui.theme.TodoListTheme

@Composable
fun AddEditScreen() {
    AddEditContent(onNavigateBack = {})
}

@Composable
fun AddEditContent(onNavigateBack: () -> Unit) {
    AppToolbar(
        title = stringResource(R.string.add_edit_screen_title),
        showBackButton = true,
        onBackClick = onNavigateBack,
        actions = {
            IconButton(onClick = { /* Favoritar */ }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = stringResource(R.string.add_edit_favorite_description),
                    tint = colorResource(id = R.color.white))
            }
        }
    ) { paddingValues ->
        //Esse Box faz necessário para que o scaffold não ocupe toda a fique abaixo da toolbar
        Box(modifier = Modifier.padding(paddingValues)) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.add_edit_save_description)
                        )
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .consumeWindowInsets(padding)
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.
                        fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(stringResource(R.string.add_edit_title_placeholder)) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.
                        fillMaxWidth(),
                        value = "",
                        onValueChange = {},
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
        AddEditContent(onNavigateBack = {})
    }
}