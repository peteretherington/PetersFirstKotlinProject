package com.applydigital.petersfirstkotlinproject

class Product (
    var id: Int,
    var name: String,
    var price: Double,
    private var image: String
        ) {
    val imageUrl get() = "https://firtman.github.io/coffeemasters/api/images/${this.image}"
}

class Category(var name: String, var products: MutableList<Product>)

class CartItem(var product: Product, var quantity: Int)