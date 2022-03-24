package com.revature.roomdbexample.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.roomdbexample.R
import com.revature.roomdbexample.model.datamodels.Customer
import com.revature.roomdbexample.viewmodels.CustomerViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*
import kotlin.random.Random

@InternalCoroutinesApi
@Composable
fun CustomerList(customerViewModel: CustomerViewModel) {
    val customerList = customerViewModel.fetchAllCustomers().observeAsState(arrayListOf())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
            LazyColumn(
                content = {
                    items(
                        count = customerList.value.size,
                        itemContent = { customer ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                content = {
                                val color =
                                    Color(
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    Random.nextInt(256)
                                    )
                                    Box(
                                        content = {
                                            Text(
                                                text = (customerList.value[customer].name ?: "")[0]
                                                    .uppercase(),
                                                fontSize = 24.sp,
                                                color = color
                                            )
                                        },
                                        modifier = Modifier
                                            .size(80.dp)
                                            .border(
                                                width = 1.2.dp,
                                                color = color,
                                                shape = CircleShape),
                                        contentAlignment = Alignment.Center,
                                    )
                                    Spacer(modifier = Modifier.size(16.dp))
                                    Column(
                                        modifier = Modifier.weight(2F),
                                        content = {
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(
                                                text = customerList.value[customer].name
                                                    .toString(),
                                                fontSize = 16.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                        )
                                            Text(
                                                text = customerList.value[customer].gender
                                                    .toString(),
                                                color = Color.Black,
                                                fontSize = 14.6.sp
                                            )
                                            Text(
                                                text = customerList.value[customer].email
                                                    .toString(),
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
                                            .clickable(
                                                onClick = {
                                                    customerViewModel
                                                        .deleteCustomerById(
                                                            customerList.value[customer].id)
                                            })
                                    )
                                })
                        })
                })
        })
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
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    customerViewModel.deleteCustomerById(customer.id)
                }
        )
    }
}