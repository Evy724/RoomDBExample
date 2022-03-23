package com.revature.roomdbexample.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.revature.roomdbexample.R
import com.revature.roomdbexample.datamodels.Customer
import com.revature.roomdbexample.viewmodels.CustomerViewModel
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
fun DisplayCustomer(customerViewModel: CustomerViewModel, customer: Customer) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(
                width = 5.dp,
                MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(5.dp)

            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.rose),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Column {
            Text(
                text = "Name: ",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Gender: ",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Email: ",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1)
        }
        Column {

            Text(text = customer.name?:"Not Loaded",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = customer.gender?:"Not Loaded",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = customer.email?:"Not Loaded",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.body1)
        }
        Image(
            painter = painterResource(id = R.drawable.cucumber),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
                .clickable {
                    customerViewModel.deleteCustomerById(customer.id)
                }
        )
    }
}
@InternalCoroutinesApi
@Composable
fun InsertCustomer(customerViewModel:CustomerViewModel){

    var sName by rememberSaveable { mutableStateOf("") }

    var sEmail by rememberSaveable { mutableStateOf("") }

    var sGender by rememberSaveable { mutableStateOf("") }


    TextField(
        value = sName,
        onValueChange = { sName = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text("Enter Name:") }
    )

    Spacer(Modifier.size(5.dp))

    TextField(
        value = sEmail,
        onValueChange = { sEmail = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = {Text("Enter Email:")},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ))

    Spacer(Modifier.size(5.dp))

    TextField(
        value = sGender,
        onValueChange = { sGender = it },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = {Text("Enter Gender:")})

    Spacer(Modifier.size(5.dp))

    Button(
        onClick = {
            customerViewModel.insertCustomer(
                customer = Customer(
                    name = sName,
                    gender = sGender,
                    email = sEmail
                )
            )
        },
        Modifier
            .fillMaxWidth(.5f)
            .height(60.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)) {
        Text(text = "Create User")
    }
}
