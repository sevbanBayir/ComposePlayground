package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val titleResId: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        titleResId = "Home",
        icon = Icons.Default.Home
    )

    object Search : BottomNavItem(
        route = Screen.Search.route,
        titleResId = "Search",
        icon = Icons.Default.Search
    )
}