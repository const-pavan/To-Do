package com.pansgami.to_do.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.pansgami.to_do.Util.Action
import com.pansgami.to_do.ui.theme.topAppBarBackgroundColor
import com.pansgami.to_do.ui.theme.topAppBarContentColor
import com.pansgami.to_do.R
import com.pansgami.to_do.componets.DisplayAlertDialog
import com.pansgami.to_do.data.modal.Priority
import com.pansgami.to_do.data.modal.ToDoTask

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action)->Unit
){
    if(selectedTask==null)
    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    else
    {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen =navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action)->Unit
){
    TopAppBar(
        navigationIcon ={
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = "Add Task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }

    )
}

@Composable
fun BackAction(
    onBackClicked: (Action)->Unit
){
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
fun AddAction(
    onAddClicked: (Action)->Unit
){
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id =R.string.add_task ),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}


@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action)->Unit
){
    TopAppBar(
        navigationIcon ={
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        }

    )
}
@Composable
fun CloseAction(
    onCloseClicked: (Action)->Unit
){
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action)->Unit
){
    var openDialog by remember { mutableStateOf(false)  }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task,selectedTask.title),
        message = stringResource(id = R.string.delete_task_conformation,selectedTask.title) ,
        openDialog = openDialog,
        closeDialog = { openDialog=false },
        onYesClicked = {
            navigateToListScreen(Action.DELETE)
        }
    )

    DeleteAction(onDeleteClicked = {
        openDialog=true
    })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}


@Composable
fun DeleteAction(
    onDeleteClicked: ()->Unit
){
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}
@Composable
fun UpdateAction(
    onUpdateClicked: (Action)->Unit
){
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
@Preview
fun NewTaskAppBarPreview(){
    NewTaskAppBar(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
fun ExistingTaskAppBarPreview(){
    ExistingTaskAppBar(selectedTask = ToDoTask(
        id=0,
        title = "pavan",
        description = "some random text",
        priority = Priority.HIGH
    ),
        navigateToListScreen = {})
}