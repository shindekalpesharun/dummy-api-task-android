package com.shindekalpesharun.dummyapitask.posts.repository

import com.shindekalpesharun.dummyapitask.data.remote.ApiServiceInterface
import com.shindekalpesharun.dummyapitask.posts.model.response.PostCommentsResponse
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponse
import retrofit2.Response
import javax.inject.Inject

class PostsRepository @Inject constructor(private val apiService: ApiServiceInterface) {
    /**
     * Get posts
     *
     * @return
     */
    suspend fun getPosts(): Response<PostsResponse> {
        return apiService.getPosts()
    }

    suspend fun getCommentsForPost(postId: Int): Response<PostCommentsResponse> {
        return apiService.getCommentsForPost(postId = postId)
    }
}