package dev.belalkhan.netrosample.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")

    object Users : Screen("users")

    object Posts : Screen("posts") {
        const val ROUTE_WITH_ARG = "posts/{userId}"

        fun createRoute(userId: Int) = "posts/$userId"
    }
}
