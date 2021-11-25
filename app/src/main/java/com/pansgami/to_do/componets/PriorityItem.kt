package com.pansgami.to_do.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pansgami.to_do.data.modal.Priority
import com.pansgami.to_do.ui.theme.LARGE_PANDDING
import com.pansgami.to_do.ui.theme.PRIORITY_INDICATOR_SIZE
import com.pansgami.to_do.ui.theme.Typography

@Composable
fun priorityItem(priority: Priority){

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE) ){
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.padding(start = LARGE_PANDDING),
            text = priority.name,
            style = Typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
    }

}

@Composable
@Preview
fun priorityItemPreview()
{
    priorityItem(priority = Priority.LOW)
}