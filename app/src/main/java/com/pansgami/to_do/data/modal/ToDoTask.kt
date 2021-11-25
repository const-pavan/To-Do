package com.pansgami.to_do.data.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pansgami.to_do.Util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val title: String,
    val description: String,
    val priority: Priority

)