package com.revature.roomdbexample.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.revature.roomdbexample.datamodels.Customer
import com.revature.roomdbexample.view.ui.theme.RoomDBExampleTheme
import com.revature.roomdbexample.viewmodel.CustomerViewModel
import java.util.*
import kotlin.random.Random.Default.nextInt
import kotlin.random.Random.Default.nextInt

//Prepare a screen for inserting a customer
//Insert the customer with one Composable

//Create an Edit customer screen, select a customer and go to next screen, allowing you edit information
//change some data, click update and repopulate

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CustomerList(customerViewModel)
                }
            }
        }
    }
}

@Composable
fun CustomerList(customerViewModel: CustomerViewModel) {

    val customerList = customerViewModel.fetchAllCustomers().observeAsState(arrayListOf())

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                backgroundColor = Color.Red,
                text = {
                    Text(text = "Customer", color = Color.White)
                },
                onClick = {
                    val name = UUID.randomUUID().toString()
                    customerViewModel.insertCustomer(
                        Customer(
                            name = name,
                            gender = "male",
                            email = "evan@revature.net"
                        ),
                    )
                }, icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "image",
                        tint = Color.White
                    )
                })
        },
        content = {
            LazyColumn(content = {
                items(
                    count = customerList.value,
                    itemContent = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp), content = {
                                Box(
                                    content = {
                                        Text(
                                            text = (it.name ?: "")[0].uppercase(),
                                            fontSize = 24.sp,

                                        )
                                    }, modifier = Modifier
                                        .size(80.dp),
                                    contentAlignment = Alignment.Center
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Column(
                                    modifier = Modifier.weight(2F),
                                    content = {
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = it.name?.uppercase() ?: "",
                                            fontSize = 16.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = "${it.gender}",
                                            color = Color.Black,
                                            fontSize = 14.6.sp
                                        )
                                        Text(
                                            text = "${it.email}",
                                            color = Color.Gray,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    })
                                Spacer(modifier = Modifier.size(16.dp))
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "image",
                                    tint = Color.Red, modifier = Modifier
                                        .size(30.dp)
                                        .clickable(onClick = {
                                            customerViewModel.deleteCustomerById(it.id)
                                        })
                                )
                            })
                    })
            })
        })

}