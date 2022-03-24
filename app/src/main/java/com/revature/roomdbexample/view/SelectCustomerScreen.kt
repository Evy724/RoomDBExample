package com.revature.roomdbexample.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.revature.roomdbexample.viewmodels.CustomerViewModel
import com.revature.roomdbexample.widgets.DisplayCustomer
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun SelectCustomerScreen(customerViewModel: CustomerViewModel) {

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
            SelectCustomer(customerViewModel = customerViewModel)
        }
    }
}

@InternalCoroutinesApi
@Composable
fun SelectCustomer(customerViewModel: CustomerViewModel) {

    var localId = 0

    TextField(
        value = localId.toString(),
        onValueChange = { localId = it.toInt() },
        modifier = Modifier.fillMaxWidth(.7f),
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text("Enter Id:") }
    )
    Spacer(Modifier.size(5.dp))
    Button(
        onClick = {
            customerViewModel.selectCustomerById(localId)
        },
        Modifier
            .fillMaxWidth(.5f)
            .height(60.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)) {
        Text(text = "Find Customer")
    }
}