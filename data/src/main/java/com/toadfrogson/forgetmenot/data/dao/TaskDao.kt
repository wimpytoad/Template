package com.toadfrogson.forgetmenot.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks_db")
    fun getAll(): List<SingleTaskEntity>

    @Query("SELECT * FROM tasks WHERE task_category LIKE :category")
    fun getAllInCategory(category: String): List<SingleTaskEntity>

    @Insert
    fun insertAll(tasks: List<SingleTaskEntity>)

    @Delete
    fun delete(task: SingleTaskEntity)
}