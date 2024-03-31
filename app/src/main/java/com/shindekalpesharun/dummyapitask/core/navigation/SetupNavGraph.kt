package com.shindekalpesharun.dummyapitask.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponseItem
import com.shindekalpesharun.dummyapitask.posts.presentation.screens.PostDetailsScreen
import com.shindekalpesharun.dummyapitask.posts.presentation.screens.PostsScreen

/**
 * Setup nav graph
 *
 * @param navController
 */
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.PostsScreen.route,
        route = "dummyApiTask"
    ) {

        composable(Screen.PostsScreen.route) {
            PostsScreen(navController = navController)
        }

        composable(
            Screen.PostDetailsScreen.route
        ) {
            val post: PostsResponseItem? =
                navController.previousBackStackEntry?.savedStateHandle?.get("post") // new

            if (post != null) {
                PostDetailsScreen(navController = navController, post = post)
            }
        }
    }
}