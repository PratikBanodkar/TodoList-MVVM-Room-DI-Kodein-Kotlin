package com.pratik.todo.ui.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratik.todo.R
import com.pratik.todo.data.db.entities.TodoItem
import com.pratik.todo.other.TodoListAdapter
import com.pratik.todo.ui.createtodo.TodoDetailsActivity
import kotlinx.android.synthetic.main.activity_todolist.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein

class TodoListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: TodoListViewModelFactory by instance()

    private val REQUEST_CODE_ADD_TODO = 1234
    private lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist)

        viewModel = ViewModelProviders.of(this, factory).get(TodoListViewModel::class.java)

        fab_AddTodo.setOnClickListener {
            val addTodoIntent = Intent(this, TodoDetailsActivity::class.java)
            startActivityForResult(addTodoIntent, REQUEST_CODE_ADD_TODO)
        }

        val adapter = TodoListAdapter(emptyList(), viewModel)
        val layoutManager = LinearLayoutManager(this)
        recyclerView_TodoList.adapter = adapter
        recyclerView_TodoList.layoutManager = layoutManager

        viewModel.getAllTodoItems().observe(this, Observer { todoItems ->
            adapter.todoItems = todoItems
            adapter.notifyDataSetChanged()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_TODO) {
                if (data != null) {
                    val title = data.getStringExtra("args_title")
                    val description = data.getStringExtra("args_description")
                    val completed = data.getBooleanExtra("args_completed", false)
                    val todoItem = TodoItem(title, description, completed)
                    viewModel.insertTodoItem(todoItem)
                }
            }
        }
    }

}
