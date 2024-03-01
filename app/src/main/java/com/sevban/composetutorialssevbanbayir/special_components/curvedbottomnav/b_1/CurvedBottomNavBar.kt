package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CurvedBottomNavBar(onClickNavItem: (String) -> Unit) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth(1F)
    ) {

    }
}

val bottomBarItems =
    listOf(
        BottomNavItem.Home,
        BottomNavItem.Search
    )