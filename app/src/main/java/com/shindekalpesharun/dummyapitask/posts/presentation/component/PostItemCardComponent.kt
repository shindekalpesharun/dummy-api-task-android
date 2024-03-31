package com.shindekalpesharun.dummyapitask.posts.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shindekalpesharun.dummyapitask.core.navigation.Screen
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponseItem


@Composable
fun PostItemCardComponent(item: PostsResponseItem, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "post",
                    value = item
                )
                navController.navigate(Screen.PostDetailsScreen.route)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = item.title, fontWeight = FontWeight.Bold)
            Text(
                text = item.body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}