package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1

sealed class Screen(val route: String) {
    object Home : Screen("home_page")

    object Authentication : Screen("auth_graph")

    object Search : Screen("search_graph")
}