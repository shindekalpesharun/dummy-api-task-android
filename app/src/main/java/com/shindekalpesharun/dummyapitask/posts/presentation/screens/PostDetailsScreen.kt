package com.shindekalpesharun.dummyapitask.posts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shindekalpesharun.dummyapitask.core.components.AppBarComponent
import com.shindekalpesharun.dummyapitask.core.navigation.Screen
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponseItem
import com.shindekalpesharun.dummyapitask.posts.presentation.component.PostItemCardComponent
import com.shindekalpesharun.dummyapitask.posts.viewModel.PostsViewModel

@Composable
fun PostDetailsScreen(
    postsViewModel: PostsViewModel = hiltViewModel<PostsViewModel>(),
    navController: NavController,
    post: PostsResponseItem,
) {

    val postsCommentResponse by postsViewModel.postsCommentResponse.observeAsState()
    val loading by postsViewModel.loading.observeAsState(initial = true)

    LaunchedEffect(Unit) {
        postsViewModel.getCommentsForPost(post.id) {}
    }

    Scaffold(
        topBar = { AppBarComponent(title = post.title) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(AnnotatedString(text = post.body), modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Comments", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            if (loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            strokeWidth = 2.dp
                        )
                    }
                }
            } else {
                // Pass the size of the comments list as the count parameter for the items function
                postsCommentResponse?.let { commentsResponse ->
                    items(commentsResponse.size) { index ->
                        val postCommentItem = commentsResponse[index]
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray, RoundedCornerShape(8.dp))
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    append("name - ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(postCommentItem.name)
                                    }
                                })
                                Text("email - ${postCommentItem.email}")
                                Text("comment - ${postCommentItem.body}")
                            }
                        }
                    }
                }
            }
        }
    }
}
