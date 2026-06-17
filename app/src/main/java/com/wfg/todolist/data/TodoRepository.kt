package com.wfg.todolist.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wfg.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(title: String, description: String?)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean)

    suspend fun deleteTodo(id: Long)

    fun getAllTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Long): Todo?
}