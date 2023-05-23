package com.example.bazinga.presentation.navigation

import com.example.bazinga.common.ParamsKeys

sealed class Destinations(val route: String) {
    object NewsListScreen : Destinations(Screens.NEWS_LIST_SCREEN)
    object NewsDetailsScreen :
        Destinations("${Screens.NEWS_DETAILS_SCREEN}/{${ParamsKeys.NEWS_ID}}")
}
