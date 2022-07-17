package com.toadfrogson.forgetmenot.data.repo

import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.model.TaskModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DatabaseRepo {
    val tasksList: Flow<List<TaskModel>>
    suspend fun getTasksByCategory(category: String)
    suspend fun saveTask(singleTask: TaskModel)
}

class DatabaseRepoImpl(private val dao: TaskDao) : DatabaseRepo {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override val tasksList: Flow<List<TaskModel>> =
        dao.getAll().map { list -> list.map { TaskModel.map(it) } }

    override suspend fun getTasksByCategory(category: String) {

    }

    override suspend fun saveTask(singleTask: TaskModel) {
        coroutineScope.launch(Dispatchers.IO) {
            singleTask.id
            dao.insertTask(TaskModel.convertToEntity(singleTask))
        }
    }
}