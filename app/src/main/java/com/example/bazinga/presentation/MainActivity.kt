package com.example.bazinga.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bazinga.common.ParamsKeys
import com.example.bazinga.presentation.newsDetails.NewsDetailsScreen
import com.example.bazinga.presentation.newsList.components.NewsListScreen
import com.example.bazinga.presentation.ui.theme.BazingaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BazingaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            NewsListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.NewsDetailsScreen.route + "/{${ParamsKeys.NEWS_ID}}",
                            arguments = listOf(navArgument(ParamsKeys.NEWS_ID) {
                                type = NavType.IntType
                            })
                        ) {
                            NewsDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}