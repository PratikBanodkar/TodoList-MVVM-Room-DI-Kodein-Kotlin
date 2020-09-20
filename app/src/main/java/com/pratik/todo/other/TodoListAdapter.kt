package com.pratik.todo.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pratik.todo.R
import com.pratik.todo.data.db.entities.TodoItem
import com.pratik.todo.ui.todolist.TodoListViewModel
import kotlinx.android.synthetic.main.layout_todo_list_item.view.*

class TodoListAdapter(
    var todoItems: List<TodoItem>,
    private var viewModel: TodoListViewModel
) : RecyclerView.Adapter<TodoListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var textViewTitle: TextView
        lateinit var textViewDescription: TextView
        lateinit var checkBoxTodoCompleted: CheckBox

        fun bind(currentItem: TodoItem) {
            textViewTitle = itemView.findViewById(R.id.textView_Title)
            textViewDescription = itemView.findViewById(R.id.textView_Description)
            checkBoxTodoCompleted = itemView.findViewById(R.id.checkBox_TodoCompleted)
            textViewTitle.text = currentItem.title
            textViewDescription.text = currentItem.description
            checkBoxTodoCompleted.isChecked = currentItem.completed

            checkBoxTodoCompleted.setOnCheckedChangeListener { _, isChecked ->
                currentItem.completed = isChecked
                viewModel.updateTodoItem(currentItem.id,isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_todo_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = todoItems[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}