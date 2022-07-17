package com.toadfrogson.forgetmenot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

const val DATABASE_NAME = "tasks_db"
const val DATABASE_DATA_FILENAME = "tasks_db.json"


@Database(entities = [SingleTaskEntity::class], version = 2, exportSchema = true)
abstract class TaskDB : RoomDatabase() {
    abstract fun taskDao() : TaskDao


    companion object {
        private var dbInstance: TaskDB? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context, scope: CoroutineScope): TaskDB {
            return dbInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDB::class.java,
                    DATABASE_NAME
                ).build()
                dbInstance = instance
                instance
            }
        }
    }
}