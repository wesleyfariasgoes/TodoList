package com.wfg.todolist.ui.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wfg.todolist.R
import com.wfg.todolist.domain.Todo
import com.wfg.todolist.domain.todoList
import com.wfg.todolist.domain.todoList2
import com.wfg.todolist.domain.todoList3
import com.wfg.todolist.ui.components.AppToolbar
import com.wfg.todolist.ui.components.TodoItem
import com.wfg.todolist.ui.theme.TodoListTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit
) {
    ListContent(
        onAddItemClick = navigateToAddEditScreen,
        todos = emptyList()
    )
}

@Composable
fun ListContent(
    todos: List<Todo>,
    onAddItemClick: (id: Long?) -> Unit
) {
    AppToolbar(
        title = stringResource(R.string.add_home_screen_title)
    ) { paddingValues ->
        //Esse Box faz necessário para que o scaffold não ocupe toda a fique abaixo da toolbar
        //
        Box(modifier = Modifier.padding(paddingValues)) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { onAddItemClick(null)} ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .consumeWindowInsets(padding),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(todos) { index, todo ->
                        TodoItem(
                            todo = todo,
                            onCompletedChange = {},
                            onItemClick = {},
                            onDeleteClick = {}
                        )
                        if (index < todos.lastIndex) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun ListContentPreview() {
    TodoListTheme() {
        ListContent(
            todos = listOf(
                todoList,
                todoList2,
                todoList3
            ),
            onAddItemClick = {}
        )
    }
}
