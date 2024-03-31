package com.shindekalpesharun.dummyapitask.posts.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class PostCommentsResponseItem(
    @SerializedName("body")
    val body: String, // laudantium enim quasi est quidem magnam voluptate ipsam eostempora quo necessitatibusdolor quam autem quasireiciendis et nam sapiente accusantium
    @SerializedName("email")
    val email: String, // Eliseo@gardner.biz
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("name")
    val name: String, // id labore ex et quam laborum
    @SerializedName("postId")
    val postId: Int // 1
) : Parcelable