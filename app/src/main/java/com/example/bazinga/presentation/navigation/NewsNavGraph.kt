package com.example.bazinga.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bazinga.common.ParamsKeys
import com.example.bazinga.presentation.newsDetails.NewsDetailsScreen
import com.example.bazinga.presentation.newsList.components.NewsListScreen

@Composable
fun NewsNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationActions: NewsNavigation = NewsNavigation(navController)

) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NewsListScreen.route
    ) {
        composable(
            route = Destinations.NewsListScreen.route
        ) {
            NewsListScreen(
                onItemClickAction = { id -> navigationActions.navigateToNewsDetails(id) }
            )
        }
        composable(
            route = Destinations.NewsDetailsScreen.route,
            arguments = listOf(navArgument(ParamsKeys.NEWS_ID) {
                type = NavType.IntType
            })
        ) {
            NewsDetailsScreen()
        }
    }
}