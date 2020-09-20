package com.pratik.todo.ui.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.todo.data.repositories.TodoRepository

@Suppress("UNCHECKED_CAST")
class TodoListViewModelFactory(private val repository: TodoRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoListViewModel(repository) as T
    }
}