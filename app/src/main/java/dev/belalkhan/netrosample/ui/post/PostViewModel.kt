package dev.belalkhan.netrosample.ui.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.belalkhan.netrosample.posts.PostsApiService
import dev.belalkhan.netrosample.posts.models.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel
    @Inject
    constructor(
        private val postApiService: PostsApiService,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val userId: Int = savedStateHandle["userId"] ?: -1

        private val _postState = MutableStateFlow<PostState>(PostState.Loading)
        val postState: StateFlow<PostState> = _postState.asStateFlow()

        init {
            getPosts()
        }

        private fun getPosts() {
            viewModelScope.launch {
                try {
                    val response = postApiService.getUserPosts(userId.toString())
                    _postState.value = PostState.Success(response.posts)
                } catch (e: Exception) {
                    _postState.value = PostState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

sealed class PostState {
    object Loading : PostState()

    data class Success(val posts: List<Post>) : PostState()

    data class Error(val message: String) : PostState()
}
