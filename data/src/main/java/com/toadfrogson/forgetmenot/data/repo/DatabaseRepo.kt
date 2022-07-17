package com.toadfrogson.forgetmenot.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.model.TaskModel
import kotlinx.coroutines.*

interface DatabaseRepo {
    val tasksList: LiveData<List<TaskModel>>
    fun getAllTasks()
    suspend fun getTasksByCategory(category: String)
    suspend fun saveTask(taskEntity: SingleTaskEntity)
}

class DatabaseRepoImpl(private val dao: TaskDao) : DatabaseRepo {
    val searchResult = MutableLiveData<List<SingleTaskEntity?>>()
    override val tasksList: LiveData<List<TaskModel>> = convertEntities(dao.getAll())

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun getAllTasks(){

    }

    override suspend fun getTasksByCategory(category: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = findTasks(category).await()
        }
    }

    override suspend fun saveTask(taskEntity: SingleTaskEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.insertTask(taskEntity)
        }
    }


    private fun findTasks(category: String): Deferred<List<SingleTaskEntity?>> =
        coroutineScope.async(Dispatchers.IO) { return@async dao.getAllInCategory(category) }

    private fun convertEntities(entities: LiveData<List<SingleTaskEntity?>>) : LiveData<List<TaskModel>> {
        val list = mutableListOf<TaskModel>()
        entities.value?.forEach {
            list.add(TaskModel.map(it))
        }
        val data = MutableLiveData<List<TaskModel>>()
        data.value = list
        return data
    }
}