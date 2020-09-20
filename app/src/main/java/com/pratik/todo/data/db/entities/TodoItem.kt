package com.pratik.todo.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItem(
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "description")
    var description : String,
    @ColumnInfo(name = "completed")
    var completed : Boolean
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null
}