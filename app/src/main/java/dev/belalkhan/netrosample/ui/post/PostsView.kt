//package dev.belalkhan.netrosample.ui.post
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import dev.belalkhan.netrosample.posts.models.Post
//
//@Composable
//fun PostsView(viewModel: PostViewModel = hiltViewModel()) {
//    val postState = viewModel.postState.collectAsStateWithLifecycle()
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        PostsHeader()
//
//        when (val state = postState.value) {
//            is PostState.Loading -> {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            is PostState.Success -> {
//                LazyColumn(
//                    modifier =
//                        Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp),
//                ) {
//                    items(state.posts) { post ->
//                        PostCard(post)
//                    }
//                }
//            }
//
//            is PostState.Error -> {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    Text(
//                        text = "Error: ${state.message}",
//                        color = MaterialTheme.colorScheme.error,
//                        style = MaterialTheme.typography.bodyLarge,
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PostsHeader() {
//    Box(
//        modifier =
//            Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.primary)
//                .padding(vertical = 20.dp),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(
//            text = "Posts",
//            style = MaterialTheme.typography.headlineMedium,
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//        )
//    }
//}
//
//@Composable
//fun PostCard(post: Post) {
//    Card(
//        modifier =
//            Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
//    ) {
//        Column(
//            modifier =
//                Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//        ) {
//            Text(
//                text = post.title,
//                style = MaterialTheme.typography.titleLarge,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.primary,
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = post.body,
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurface,
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Posted by User: ${post.userId}",
//                style = MaterialTheme.typography.bodySmall,
//                fontStyle = FontStyle.Italic,
//                color = MaterialTheme.colorScheme.secondary,
//            )
//        }
//    }
//}
