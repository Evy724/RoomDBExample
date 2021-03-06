package com.revature.roomdbexample.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.revature.roomdbexample.model.datamodels.Customer
import com.revature.roomdbexample.viewmodels.CustomerViewModel
import com.revature.roomdbexample.widgets.DisplayCustomer
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun InsertCustomerScreen(customerViewModel: CustomerViewModel) {

    val customerList = customerViewModel.fetchAllCustomers().observeAsState(arrayListOf())

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .border(
                        width = 5.dp,
                        color = MaterialTheme.colors.primary
                    )
            ) {
                items(customerList.value.size) {
                    DisplayCustomer(customerViewModel, customerList.value[it])
                }
            }
            InsertCustomer(customerViewModel = customerViewModel)
        }
    }
}

@InternalCoroutinesApi
@Composable
fun InsertCustomer(customerViewModel: CustomerViewModel){

    var saveName by rememberSaveable { mutableStateOf("") }

    var saveEmail by rememberSaveable { mutableStateOf("") }

    var saveGender by rememberSaveable { mutableStateOf("") }


    TextField(
        value = saveName,
        onValueChange = { saveName = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text("Enter Name:") }
    )

    Spacer(Modifier.size(5.dp))

    TextField(
        value = saveEmail,
        onValueChange = { saveEmail = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = {Text("Enter Email:")},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ))

    Spacer(Modifier.size(5.dp))

    TextField(
        value = saveGender,
        onValueChange = { saveGender = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = {Text("Enter Gender:")})

    Spacer(Modifier.size(5.dp))

    Button(
        onClick = {
            customerViewModel.insertCustomer(
                customer = Customer(
                    name = saveName,
                    gender = saveGender,
                    email = saveEmail
                )
            )
        },
        Modifier
            .fillMaxWidth(.5f)
            .height(60.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)) {
        Text(text = "Create Customer")
    }
}