package com.pansgami.to_do.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pansgami.to_do.data.modal.Priority
import com.pansgami.to_do.ui.theme.LARGE_PANDDING
import com.pansgami.to_do.R
import com.pansgami.to_do.componets.PriorityDropDown
import com.pansgami.to_do.ui.theme.MEDIUM_PANDDING

@Composable
fun TaskContent(
    title:String,
    onTitleChange:(String)->Unit,
    description:String,
    onDescriptionChange:(String)->Unit,
    priority: Priority,
    onPrioritySelected: (Priority)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PANDDING)

    ) {


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {
            onTitleChange(it)
            },
            label = {
                Text(text = stringResource(id = R.string.title) )
            },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )

        Divider(
            modifier = Modifier.height(MEDIUM_PANDDING),
            color = MaterialTheme.colors.background
        )

        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = {
                onDescriptionChange(it)
            },
            label = { Text(text = stringResource(id = R.string.description))},
            textStyle = MaterialTheme.typography.body1

        )


    }
}

@Composable
@Preview
fun TaskContentPreview()
{
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}