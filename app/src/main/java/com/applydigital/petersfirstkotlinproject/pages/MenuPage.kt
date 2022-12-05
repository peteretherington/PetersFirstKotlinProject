package com.applydigital.petersfirstkotlinproject.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.applydigital.petersfirstkotlinproject.DataManager
import com.applydigital.petersfirstkotlinproject.Product
import com.applydigital.petersfirstkotlinproject.R
import com.applydigital.petersfirstkotlinproject.ui.theme.Alternative1
import com.applydigital.petersfirstkotlinproject.ui.theme.CardBackground
import com.applydigital.petersfirstkotlinproject.ui.theme.Primary

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ProductItem(product: Product, onAdd: (Product)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.format(2)} ea")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Alternative1,
                    contentColor = Color.White
                ),
                onClick = {
                    onAdd(product)
                },
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
fun MenuPage(dataManager: DataManager) {
    LazyColumn {
        items(dataManager.menu) { category ->
            Text(
                category.name,
                color = Primary,
                modifier = Modifier
                    .padding(10.dp, 20.dp, 10.dp, 10.dp)
            )
            category.products.forEach { product ->
                Card(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .background(CardBackground)
                        .padding(12.dp)
                ) {
                    ProductItem(product, onAdd = { productToAdd ->
                        dataManager.cartAdd(productToAdd)
                    })
                }
            }
        }
    }
}
