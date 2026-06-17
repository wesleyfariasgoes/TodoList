package com.wfg.todolist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wfg.todolist.domain.Todo
import com.wfg.todolist.domain.todoList
import com.wfg.todolist.domain.todoList2
import com.wfg.todolist.ui.theme.TodoListTheme

@Composable
fun TodoItem(
    todo: Todo,
    onCompletedChange: (Boolean) -> Unit = {},
    onItemClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    modifier: Modifier = Modifier) {
    //componente de superficie - design de borda, arredondamento, cor de fundo
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline)
    ) {
        //Pôr os componentes organizados em linha, um ao lado do outro
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = onCompletedChange
            )

            Spacer(modifier = Modifier.width(8.dp))
            //é um layout que organiza os componentes em coluna, um abaixo do outro verticalmente ou horizontalmente
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                todo.description?.let {
                    Text(
                        text = todo.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = todo.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemPreview() {
    TodoListTheme {
        TodoItem(
            todo = todoList
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemPreviewFalse() {
    TodoListTheme {
        TodoItem(
            todo = todoList2
        )
    }
}