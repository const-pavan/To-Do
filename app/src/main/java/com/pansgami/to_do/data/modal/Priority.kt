package com.pansgami.to_do.data.modal

import androidx.compose.ui.graphics.Color
import com.pansgami.to_do.ui.theme.HighPriorityColor
import com.pansgami.to_do.ui.theme.LowPriorityColor
import com.pansgami.to_do.ui.theme.MediumPriorityColor
import com.pansgami.to_do.ui.theme.NonePriorityColor


enum class Priority(val color:Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)

}