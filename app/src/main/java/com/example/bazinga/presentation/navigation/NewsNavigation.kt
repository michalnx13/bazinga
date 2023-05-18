package com.example.bazinga.presentation.navigation

import androidx.navigation.NavHostController
import com.example.bazinga.common.Screens

class NewsNavigation(private val navController: NavHostController) {

    fun navigateToNewsDetails(newsId: Int) {
        navController.navigate(Screens.NEWS_DETAILS_SCREEN + "/${newsId}")
    }
}