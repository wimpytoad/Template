package com.toadfrogson.forgetmenot.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.toadfrogson.forgetmenot.data.TaskDB
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksDatabaseWorker(context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val task = object : TypeToken<List<SingleTaskEntity>>() {}.type
                        val taskList: List<SingleTaskEntity> = Gson().fromJson(jsonReader, task)

                        val database = TaskDB.getInstance(applicationContext)
                        database.taskDao().insertAll(taskList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "TasksDatabaseWorker"
        const val KEY_FILENAME = "TASKS_DATA_FILENAME"
    }
}