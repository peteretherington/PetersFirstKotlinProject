package com.applydigital.petersfirstkotlinproject.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.applydigital.petersfirstkotlinproject.CartItem
import com.applydigital.petersfirstkotlinproject.DataManager
import com.applydigital.petersfirstkotlinproject.Product
import com.applydigital.petersfirstkotlinproject.ui.theme.Primary

@Composable
fun CartItem(it: CartItem, onDelete: (Product)->Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text("${it.quantity}x")
            Text(it.product.name,
                modifier = Modifier.width(150.dp)
            )
            Text("$${(it.quantity*it.product.price).format(2)}",
                modifier = Modifier.width(50.dp)
            )
            Image(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                colorFilter = ColorFilter.tint(Primary),
                modifier = Modifier.clickable {
                    onDelete(it.product)
                }
            )
        }
    }
}

@Composable
fun OrderPage(dataManager: DataManager) {
    LazyColumn() {
        if (dataManager.cart.isEmpty()) {
            item {
                Text(
                    "Your order is empty.",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(dataManager.cart) { cartItem ->
                Spacer(modifier = Modifier.height(10.dp))
                CartItem(cartItem, onDelete = { delItem ->
                    dataManager.cartRemove(delItem)
                })
            }

            var orderTotal = dataManager.cartTotal().format(2)
            item {
                Text("Total: $$orderTotal",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}
