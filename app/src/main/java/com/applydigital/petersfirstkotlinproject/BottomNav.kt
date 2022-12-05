package com.applydigital.petersfirstkotlinproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.applydigital.petersfirstkotlinproject.ui.theme.Alternative1
import com.applydigital.petersfirstkotlinproject.ui.theme.OnPrimary

data class NavPage(var name: String, var route: String, var icon: ImageVector)

object Routes {
    var MenuPage = NavPage("Menu", route = "menu", icon = Icons.Outlined.Menu)
    var OffersPage = NavPage("Offers", route = "offers", icon = Icons.Outlined.Star)
    var OrderPage = NavPage("Order", route = "order", icon = Icons.Outlined.ShoppingCart)
    var InfoPage = NavPage("Info", route = "info", icon = Icons.Outlined.Info)

    val pages = listOf(MenuPage, OffersPage, OrderPage, InfoPage)
}

@Composable
fun NavBarItem(page: NavPage, selected: Boolean = false, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 12.dp)) {
        Image(
            imageVector = page.icon,
            contentDescription = page.name,
            colorFilter = ColorFilter.tint(
                if (selected) Alternative1 else OnPrimary
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(24.dp)
        )
        Text(page.name,
            fontSize = 12.sp,
            color = if (selected) Alternative1 else OnPrimary
        )
    }
}

@Composable
fun NavBar(selectedRoute: String = Routes.MenuPage.route, onChange: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 16.dp)
    ) {
        for (page in Routes.pages) {
            NavBarItem(
                page = page,
                selected = selectedRoute == page.route,
                modifier = Modifier.clickable {
                    onChange(page.route)
                }
            )
        }
    }
}