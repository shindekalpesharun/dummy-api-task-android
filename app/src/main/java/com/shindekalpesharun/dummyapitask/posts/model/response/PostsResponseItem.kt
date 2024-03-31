package com.shindekalpesharun.dummyapitask.posts.model.response


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PostsResponseItem(
    @SerializedName("body")
    val body: String, // quia et suscipitsuscipit recusandae consequuntur expedita et cumreprehenderit molestiae ut ut quas totamnostrum rerum est autem sunt rem eveniet architecto
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("title")
    val title: String, // sunt aut facere repellat provident occaecati excepturi optio reprehenderit
    @SerializedName("userId")
    val userId: Int // 1
) : Parcelable