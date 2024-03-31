package com.shindekalpesharun.dummyapitask.posts.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shindekalpesharun.dummyapitask.posts.model.response.PostCommentsResponse
import com.shindekalpesharun.dummyapitask.posts.model.response.PostsResponse
import com.shindekalpesharun.dummyapitask.posts.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {
    /* when it initialize the class then automatically call init */
    private val _loading = MutableLiveData<Boolean>().apply { value = false }
    val loading: LiveData<Boolean> get() = _loading

    //  post response
    private val _postsResponse: MutableLiveData<PostsResponse?> = MutableLiveData()
    val postsResponse: LiveData<PostsResponse?> get() = _postsResponse

    //  post comment response
    private val _postsCommentResponse: MutableLiveData<PostCommentsResponse?> = MutableLiveData()
    val postsCommentResponse: LiveData<PostCommentsResponse?> get() = _postsCommentResponse


    /* when it initialize the class then automatically call init */
    init {
        _loading.value = true
    }

    fun getPosts(callback: (Boolean) -> Unit): Unit {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true) // Show progress bar
            try {
                val response: Response<PostsResponse> = repository.getPosts()
                if (response.isSuccessful) {
                    Log.d(TAG, "getPosts: $response.body()")
                    _postsResponse.postValue(response.body())
                    callback(true)
                } else {
                    callback(false)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getPosts: $e")
                callback(false)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun getCommentsForPost(postId: Int, callback: (Boolean) -> Unit): Unit {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true) // Show progress bar
            try {
                val response: Response<PostCommentsResponse> = repository.getCommentsForPost(postId = postId)
                if (response.isSuccessful) {
                    Log.d(TAG, "getPosts: $response.body()")
                    _postsCommentResponse.postValue(response.body())
                    callback(true)
                } else {
                    callback(false)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getPosts: $e")
                callback(false)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    /**
     * On cleared
     *
     */
    override fun onCleared() {
        super.onCleared()
    }
}