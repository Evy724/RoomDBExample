package com.revature.roomdbexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.revature.roomdbexample.viewmodel.InsertViewModel

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(androidx.appcompat.R.layout.abc_activity_chooser_view)

        val customerRepository = ViewModelProvider(this).get(InsertViewModel::class.java)

        }
}