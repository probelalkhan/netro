package dev.belalkhan.netrosample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.belalkhan.netrosample.ui.auth.AuthView
import dev.belalkhan.netrosample.ui.post.PostsView
import dev.belalkhan.netrosample.ui.users.UsersView

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route,
        modifier = modifier,
    ) {
        composable(Screen.Auth.route) {
            AuthView(
                onNavigate = { navController.navigate(it) },
            )
        }

        composable(Screen.Users.route) {
            UsersView(
                viewModel = hiltViewModel(),
                onUserClick = { userId ->
                    navController.navigate(Screen.Posts.createRoute(userId))
                },
            )
        }

        composable(
            route = Screen.Posts.ROUTE_WITH_ARG,
            arguments = listOf(navArgument("userId") { type = NavType.IntType }),
        ) {
            PostsView()
        }
    }
}
