package com.pansgami.to_do.ui.screens.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.pansgami.to_do.MainActivity
import com.pansgami.to_do.R
import com.pansgami.to_do.Util.Action
import com.pansgami.to_do.Util.SearchAppBarState
import com.pansgami.to_do.ui.theme.FabBackgroundColor
import com.pansgami.to_do.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    LaunchedEffect(key1 = true){
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()

    val searchTasks by sharedViewModel.searchTasks.collectAsState()

    val sortState by sharedViewModel.sortState.collectAsState()

    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()

    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState:SearchAppBarState
        by sharedViewModel.searchAppBarState

    val searchTextState:String
        by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()



    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModel.HandleDatabaseAction(action=action) },
        taskTitle = sharedViewModel.title.value,
        onUndoClicked = {
            sharedViewModel.action.value=it
        },
        action = action
    )


    Scaffold(
        scaffoldState = scaffoldState,
        topBar={
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
               },
        content={
            Column() {
                ListContent(
                    allTasks = allTasks,
                    searchTasks = searchTasks,
                    lowPriorityTasks = lowPriorityTasks,
                    highPriorityTasks=highPriorityTasks,
                    sortState = sortState,
                    searchAppBarState = searchAppBarState,
                    navigationToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = {action, toDoTask ->
                        sharedViewModel.action.value=action
                        sharedViewModel.updateTaskFields(selectedTask = toDoTask)

                    }
                );
                //AdvertView()
            }


        },
        floatingActionButton ={
            ListFab(onFabClicked = navigateToTaskScreen)
        }



    )

}
@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
){
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.FabBackgroundColor
    )
    {
        Icon(imageVector = Icons.Filled.Add,

            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: ()->Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
){
    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    
    LaunchedEffect(key1 = action){
        if(action != Action.NO_ACTION){
            scope.launch {
                val snackBarResult =scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action=action,taskTitle = taskTitle) ,
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action=action,
                    snackBarResult=snackBarResult,
                    onUndoClicked = onUndoClicked

                )
            }
        }
    }

}
private fun setMessage(action: Action, taskTitle: String) :String{
    return when(action){
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name}: $taskTitle"
    }

}

private fun setActionLabel(action: Action):String{
    return if(action.name == "DELETE"){
        "UNDO"
    }
    else
    {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action)->Unit
){
    if(snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE){
        onUndoClicked(Action.UNDO)
    }
}

/*
@Composable
fun AdvertView(modifier: Modifier = Modifier) {

        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    adSize = AdSize.BANNER
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
}*/

@Preview(showBackground = true)
@Composable
fun AdvertPreview() {
    //AdvertView()
}
