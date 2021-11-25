package com.pansgami.to_do.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.pansgami.to_do.Util.Action
import com.pansgami.to_do.data.modal.Priority
import com.pansgami.to_do.data.modal.ToDoTask
import com.pansgami.to_do.ui.viewmodel.SharedViewModel

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action)->Unit,
){

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority:Priority by sharedViewModel.priority

    val context = LocalContext.current
    
    BackHandler(onBackPressed = {navigateToListScreen(Action.NO_ACTION) })
    
    Scaffold(
        topBar = {
                 TaskAppBar(
                     selectedTask = selectedTask,
                     navigateToListScreen = {action ->
                        if(action == Action.NO_ACTION)
                        {
                            navigateToListScreen(action)
                        }
                        else
                        {
                            if(sharedViewModel.validateFields())
                            {
                                navigateToListScreen(action)
                            }else
                            {
                                displyToast(context = context)
                            }
                        }
                     }
                 )

                },
        content = {
                    TaskContent(
                        title = title,
                        onTitleChange = {
                            sharedViewModel.updateTitle(it)
                        },
                        description = description,
                        onDescriptionChange = {
                            sharedViewModel.description.value =it
                        },
                        priority = priority,
                        onPrioritySelected = {
                            sharedViewModel.priority.value =it
                        }
                    )
                }

    )

    
}

fun displyToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty",
        Toast.LENGTH_SHORT
    ).show()

}
@Composable
fun BackHandler(
    backDispatchar: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: ()->Unit
){
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallBack = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }

        }
    }
    
    DisposableEffect(key1 = backDispatchar){
        backDispatchar?.addCallback(backCallBack)

        onDispose {
            backCallBack.remove()
        }
    }
}