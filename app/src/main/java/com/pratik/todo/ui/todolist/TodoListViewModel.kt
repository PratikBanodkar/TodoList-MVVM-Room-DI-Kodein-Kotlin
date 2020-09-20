package com.pratik.todo.ui.todolist

import androidx.lifecycle.ViewModel
import com.pratik.todo.data.db.entities.TodoItem
import com.pratik.todo.data.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {

    fun insertTodoItem(item: TodoItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertTodoItem(item)
    }

    fun deleteTodoItem(item: TodoItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteTodoItem(item)
    }

    fun updateTodoItem(itemId: Int?,completed: Boolean) = CoroutineScope(Dispatchers.Main).launch {
        repository.updateTodoItem(itemId,completed)
    }

    fun getAllTodoItems() = repository.getAllTodoItems()

}