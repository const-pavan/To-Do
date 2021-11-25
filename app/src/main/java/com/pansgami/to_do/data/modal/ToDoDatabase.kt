package com.pansgami.to_do.data.modal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pansgami.to_do.data.ToDoDao

@Database(entities = [ToDoTask::class],version = 1,exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun ToDoDao():ToDoDao

}