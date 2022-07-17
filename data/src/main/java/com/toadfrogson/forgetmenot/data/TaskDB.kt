package com.toadfrogson.forgetmenot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.worker.TasksDatabaseWorker
import com.toadfrogson.forgetmenot.data.worker.TasksDatabaseWorker.Companion.KEY_FILENAME
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

const val DATABASE_NAME = "tasks_db"
const val DATABASE_DATA_FILENAME = "tasks_db.json"


@Database(entities = [SingleTaskEntity::class], version = 1)
abstract class TaskDB : RoomDatabase() {
    abstract fun taskDao() : TaskDao


    companion object {
        @Volatile private var instance: TaskDB? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context) : TaskDB {
            return instance?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TaskDB {
            return Room.databaseBuilder(context, TaskDB::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<TasksDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to DATABASE_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}