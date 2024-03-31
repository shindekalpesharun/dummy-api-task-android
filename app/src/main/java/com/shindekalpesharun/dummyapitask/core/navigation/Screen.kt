package com.shindekalpesharun.dummyapitask.core.navigation

sealed class Screen(val route: String) {
    data object PostsScreen : Screen("PostsScreen")
    data object PostDetailsScreen : Screen("PostDetailsScreen")
}