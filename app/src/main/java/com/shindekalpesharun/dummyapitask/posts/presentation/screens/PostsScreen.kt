package com.shindekalpesharun.dummyapitask.posts.presentation.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shindekalpesharun.dummyapitask.core.components.AppBarComponent
import com.shindekalpesharun.dummyapitask.core.navigation.Screen
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponseItem
import com.shindekalpesharun.dummyapitask.posts.presentation.component.PostItemCardComponent
import com.shindekalpesharun.dummyapitask.posts.viewModel.PostsViewModel

@Composable
fun PostsScreen(
    postsViewModel: PostsViewModel = hiltViewModel<PostsViewModel>(),
    navController: NavController,
) {
    val postsResponse by postsViewModel.postsResponse.observeAsState()
    val loading by postsViewModel.loading.observeAsState(initial = true)

    LaunchedEffect(Unit) {
        postsViewModel.getPosts() { }
    }

    Scaffold(
        topBar = { AppBarComponent(title = "Posts") }
    ) { innerPadding ->
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    strokeWidth = 2.dp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                item() {
                    postsResponse?.let { item ->
                        item.forEach { postItem ->
                            PostItemCardComponent(postItem, navController)
                        }
                    }
                }
            }
        }
    }
}
