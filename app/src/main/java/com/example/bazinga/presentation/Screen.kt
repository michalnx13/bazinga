package com.example.bazinga.presentation

import com.example.bazinga.common.ScreensRoutes

sealed class Screen(val route: String) {
    object NewsListScreen : Screen(ScreensRoutes.NEWS_LIST_SCREEN)
    object NewsDetailsScreen : Screen(ScreensRoutes.NEWS_DETAILS_SCREEN)
}
