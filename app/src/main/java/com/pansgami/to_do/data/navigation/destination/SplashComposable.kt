package com.pansgami.to_do.data.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pansgami.to_do.Util.Constants.SPLASH_SCREEN
import com.pansgami.to_do.ui.screens.splash.SplashScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
){
    composable(
        route = SPLASH_SCREEN,
        exitTransition = { _ ,_ ->
            slideOutVertically(
                targetOffsetY={-it},
                animationSpec = tween(durationMillis = 300)
            )
        }
    ){
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}