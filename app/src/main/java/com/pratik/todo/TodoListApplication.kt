package com.pratik.todo

import android.app.Application
import com.pratik.todo.data.db.TodoDatabase
import com.pratik.todo.data.repositories.TodoRepository
import com.pratik.todo.ui.todolist.TodoListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TodoListApplication:Application() , KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@TodoListApplication))
        bind() from singleton{ TodoDatabase(instance())}
        bind() from singleton{ TodoRepository(instance())}
        bind() from provider{ TodoListViewModelFactory(instance())}
    }
}