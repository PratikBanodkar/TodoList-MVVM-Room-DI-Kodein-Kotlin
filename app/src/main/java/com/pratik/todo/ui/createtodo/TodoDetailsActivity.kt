package com.pratik.todo.ui.createtodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.pratik.todo.R
import kotlinx.android.synthetic.main.activity_todo_details.*

class TodoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)
        
        textInputEditText_Title.doOnTextChanged { text, start, before, count ->
            if(text.toString().isNotEmpty()){
                textInputLayout_Title.error = ""
                textInputLayout_Title.isErrorEnabled = false
            }
        }

        button_Save.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        val title = textInputEditText_Title.text.toString()
        val description = textInputEditText_Description.text.toString()
        val completed = checkBox_CompletionStatus.isChecked
        if(title.isEmpty()){
            textInputLayout_Title.error = "Please enter a title"
            textInputLayout_Title.isErrorEnabled = true
        }

        val returnedIntent = Intent()
        returnedIntent.putExtra("args_title",title)
        returnedIntent.putExtra("args_description",description)
        returnedIntent.putExtra("args_completed",completed)
        setResult(RESULT_OK,returnedIntent)
        finish()
    }
}