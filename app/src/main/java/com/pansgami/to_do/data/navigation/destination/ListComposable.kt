package com.pansgami.to_do.data.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import com.pansgami.to_do.Util.Constants.LIST_ARGUMENT_KEY
import com.pansgami.to_do.Util.Constants.LIST_SCREEN
import com.pansgami.to_do.Util.toAction
import com.pansgami.to_do.ui.screens.list.ListScreen
import com.pansgami.to_do.ui.viewmodel.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackSatckE->

        val action = navBackSatckE.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action){
            sharedViewModel.action.value = action
        }

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}