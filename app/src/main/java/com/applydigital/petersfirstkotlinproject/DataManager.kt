package com.applydigital.petersfirstkotlinproject

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataManager(app: Application): AndroidViewModel(app) {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<CartItem> by mutableStateOf(listOf())

    init { fetchData() }

    private fun fetchData() {
        try {
            viewModelScope.launch {
                menu = API.menuService.fetchMenu()
            }
        } catch (ex: Exception) {
            // TODO: create state variable for error
        }
    }

    fun cartAdd(product: Product) {
        var found = false
        cart.forEach {
            if (it.product.id == product.id) {
                it.quantity++
                found = true
            }
        }
        if (!found) {
            cart = listOf(*cart.toTypedArray(), CartItem(product, 1))
        }
    }

    fun cartRemove(product: Product) {
        val aux = cart.toMutableList()
        aux.removeAll {
            it.product.id == product.id
        }
        cart = listOf(*aux.toTypedArray())
    }

    fun cartClear() {
        cart = listOf()
    }

    fun cartTotal(): Double {
        var sum = 0.0
        for (item in cart) {
            sum += item.quantity * item.product.price
        }
        return sum
    }
}
