package com.wfg.todolist.data

import com.wfg.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun insertTodo(title: String, description: String?) {
        val entity = TodoEntity(
            title = title,
            description = description,
            isCompleted = false
        )
        dao.insertTodo(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existingTodo = dao.getTodoById(id) ?: return
        val updatedTodo = existingTodo.copy(isCompleted = isCompleted)
        dao.insertTodo(updatedTodo)
    }

    override suspend fun deleteTodo(id: Long) {
        val existingTodo = dao.getTodoById(id) ?: return
        dao.deleteTodo(existingTodo)
    }

    override fun getAllTodos(): Flow<List<Todo>> {
        //mapeamento do Flow e em seguida mapeamento da lista de TodoEntity para Tod0
        return dao.getAllTodos().map { entities ->
            entities.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted
                )
            }
        }
    }

    override suspend fun getTodoById(id: Long): Todo? {
        return dao.getTodoById(id)?.let { entity ->
            Todo(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                isCompleted = entity.isCompleted
            )
        }
    }

}