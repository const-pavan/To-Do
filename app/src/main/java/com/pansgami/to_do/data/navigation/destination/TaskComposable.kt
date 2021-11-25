package com.pansgami.to_do.data.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import com.pansgami.to_do.Util.Action
import com.pansgami.to_do.Util.Constants.TASK_ARGUMENT_KEY
import com.pansgami.to_do.Util.Constants.TASK_SCREEN
import com.pansgami.to_do.ui.screens.task.TaskScreen
import com.pansgami.to_do.ui.viewmodel.SharedViewModel


@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = { _,_ ->
            slideInHorizontally(
                initialOffsetX = {-it},
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ){ navBackStackEntry ->
        val taskId= navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId){
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectTask.collectAsState()

        LaunchedEffect(key1 = selectedTask ){
            if(selectedTask != null || taskId == -1)
            sharedViewModel.updateTaskFields(selectedTask = selectedTask)
        }

        TaskScreen(
            selectedTask=selectedTask,
            sharedViewModel=sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )

    }
}