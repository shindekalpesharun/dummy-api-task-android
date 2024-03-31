package com.shindekalpesharun.dummyapitask.data.remote

import com.shindekalpesharun.dummyapitask.posts.model.response.PostCommentsResponse
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceInterface {
    /**
     * Get posts
     *
     * @return
     */
    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>

    @GET("posts/{postId}/comments")
    suspend fun getCommentsForPost(@Path("postId") postId: Int): Response<PostCommentsResponse>
}