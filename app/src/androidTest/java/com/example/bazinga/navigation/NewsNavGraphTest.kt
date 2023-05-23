package com.example.bazinga.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bazinga.presentation.MainActivity
import com.example.bazinga.presentation.navigation.NavigationTags.START_SCREEN_TAG
import com.example.bazinga.presentation.navigation.NewsNavGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsNavGraphTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NewsNavGraph(navController = navController)
        }
    }

    @Test
    fun newsListScreenIsDisplayedAsStartScreen() {
        composeTestRule.onNodeWithTag(START_SCREEN_TAG).assertIsDisplayed()
    }

    /* TODO fix navigatesToNewsDetailsScreen() test
    @Test
    fun navigatesToNewsDetailsScreen() {
        composeTestRule.onNodeWithTag("news_item_tag").assertIsDisplayed()
        val route = navController.currentDestination?.route
        assertEquals(route, Destinations.NewsListScreen.route)
    }  */
}