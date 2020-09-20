package com.pratik.todo.data.repositories

import com.pratik.todo.data.db.TodoDatabase
import com.pratik.todo.data.db.entities.TodoItem

class TodoRepository(private val database: TodoDatabase) {
    suspend fun insertTodoItem(item: TodoItem) = database.getTodoDao().insert(item)

    suspend fun deleteTodoItem(item: TodoItem) = database.getTodoDao().delete(item)

    suspend fun updateTodoItem(itemId: Int?,completed:Boolean) = database.getTodoDao().update(itemId,completed)

    fun getAllTodoItems() = database.getTodoDao().getAllTodoItems()
}