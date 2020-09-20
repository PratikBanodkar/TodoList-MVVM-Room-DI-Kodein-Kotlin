package com.pratik.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pratik.todo.data.db.entities.TodoItem

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Query("UPDATE todo_items set completed = :completed WHERE id=:itemId")
    suspend fun update(itemId: Int?, completed: Boolean)

    //@Query("SELECT * FROM todo_items WHERE completed = 0")
    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): LiveData<List<TodoItem>>

}