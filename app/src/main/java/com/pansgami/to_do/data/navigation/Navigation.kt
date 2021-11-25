package com.pansgami.to_do.data.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.pansgami.to_do.Util.Constants.SPLASH_SCREEN
import com.pansgami.to_do.data.navigation.destination.listComposable
import com.pansgami.to_do.data.navigation.destination.splashComposable
import com.pansgami.to_do.data.navigation.destination.taskComposable
import com.pansgami.to_do.ui.viewmodel.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    val screen = remember(navController){
        Screens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){
        splashComposable(
            navigateToListScreen = screen.splash
        )

        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable (
            navigateToListScreen = screen.task,
            sharedViewModel=sharedViewModel
        )
    }
}