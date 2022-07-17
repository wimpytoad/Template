package com.toadfrogson.forgetmenot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SingleTaskEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_description") val taskDescription: String,
    @ColumnInfo(name = "task_completion") val taskCompletion: Boolean,
    @ColumnInfo(name = "task_category") val taskCategory: String
)