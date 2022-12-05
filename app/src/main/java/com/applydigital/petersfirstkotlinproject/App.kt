package com.applydigital.petersfirstkotlinproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.applydigital.petersfirstkotlinproject.pages.InfoPage
import com.applydigital.petersfirstkotlinproject.pages.OrderPage
import com.applydigital.petersfirstkotlinproject.pages.MenuPage

@Composable
fun AppTitle() {
    TopAppBar() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Coffee Masters logo"
            )
        }
    }
}

@Composable
fun App(dataManager: DataManager) {
    var selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }

    Scaffold(
        topBar = {
            AppTitle()
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = { newRoute ->
                    selectedRoute.value = newRoute
                }
            )
        },
        content = {
            when (selectedRoute.value) {
                Routes.MenuPage.route -> MenuPage(dataManager)
                Routes.OffersPage.route -> OffersPage()
                Routes.OrderPage.route -> OrderPage(dataManager)
                Routes.InfoPage.route -> InfoPage()
            }
        }
    )
}
