package com.revature.roomdbexample

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.revature.roomdbexample.view.SelectCustomerScreen
import com.revature.roomdbexample.view.ui.theme.RoomDBExampleTheme
import com.revature.roomdbexample.viewmodels.CustomerViewModel
import kotlinx.coroutines.InternalCoroutinesApi

//Prepare a screen for inserting a customer
//Insert the customer with one Composable

//Create an Edit customer screen, select a customer and go to next screen, allowing you edit information
//change some data, click update and repopulate

//Create screen to capture customer details manually
//screen for creating and updating manually

@InternalCoroutinesApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        val customerViewModel = ViewModelProvider(this)[CustomerViewModel::class.java]

        setContent {
            RoomDBExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    SelectCustomerScreen(customerViewModel)
//                    InsertCustomerScreen(customerViewModel)
//                    CustomerList(customerViewModel)
                }
            }
        }
    }
}

