package com.pansgami.to_do.data

import androidx.room.*
import com.pansgami.to_do.data.modal.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks():Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId :Int):Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE from todo_table")
    suspend fun deleteAllTask()

    @Query("select * from todo_table where title like :searchQuery or description like :searchQuery")
    fun searchDatabse(searchQuery: String):Flow<List<ToDoTask>>

    @Query("select * from todo_table order by case when priority like 'L%' then 1 when priority like 'M%' then 2 when priority like 'H%' then 3 end ")
    fun sortByLowPriority():Flow<List<ToDoTask>>

    @Query("select * from todo_table order by case when priority like 'H%' then 1 when priority like 'M%' then 2 when priority like 'L%' then 3 end ")
    fun sortByHighPriority():Flow<List<ToDoTask>>

}