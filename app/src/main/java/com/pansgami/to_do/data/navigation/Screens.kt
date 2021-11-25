package com.pansgami.to_do.data.navigation

import androidx.navigation.NavHostController
import com.pansgami.to_do.Util.Action
import com.pansgami.to_do.Util.Constants.LIST_SCREEN
import com.pansgami.to_do.Util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {

    val splash: ()->Unit = {
        navController.navigate("list/${Action.NO_ACTION.name}"){
            popUpTo(SPLASH_SCREEN) { inclusive=true }
        }
    }

    val list: (Int) -> Unit ={ taskId ->
        navController.navigate("task/$taskId")
    }

    val task: (Action) -> Unit = {action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN) { inclusive =true}
        }
    }



}