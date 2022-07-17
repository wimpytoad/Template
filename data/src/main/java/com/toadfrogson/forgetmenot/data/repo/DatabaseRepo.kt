package com.toadfrogson.forgetmenot.data.repo

import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.model.TaskModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

interface DatabaseRepo {
    val tasksList: Flow<List<SingleTaskEntity?>>
   // fun getAllTasks(): <List<TaskModel>>
    suspend fun getTasksByCategory(category: String)
    suspend fun saveTask(singleTask: TaskModel)
}

class DatabaseRepoImpl(private val dao: TaskDao) : DatabaseRepo {
   // val searchResult = MutableLiveData<List<SingleTaskEntity?>>()
   // override var tasksList: LiveData<List<TaskModel>> = convertEntities(dao.getAll())

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override val tasksList: Flow<List<SingleTaskEntity?>> = dao.getAll()

    // override fun getAllTasks(): LiveData<List<TaskModel>> = convertEntities(dao.getAll())

    override suspend fun getTasksByCategory(category: String) {
        /*coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = findTasks(category).await()
        }*/
    }

    override suspend fun saveTask(singleTask: TaskModel) {
        coroutineScope.launch(Dispatchers.IO) {
            singleTask.id
            dao.insertTask(TaskModel.convertToEntity(singleTask))
        }
    }


  /*  private fun findTasks(category: String): Deferred<List<SingleTaskEntity?>> =
        coroutineScope.async(Dispatchers.IO) { return@async dao.getAllInCategory(category) }

    private fun getAllData(): Deferred<LiveData<List<SingleTaskEntity?>>> =
        coroutineScope.async(Dispatchers.IO) { return@async dao.getAll() }
*/

    /*private fun convertEntities(entities: Flow<List<SingleTaskEntity?>>) : Flow<List<SingleTaskEntity>> {
        val list = mutableListOf<TaskModel>()
        val dataLsit = entities
        dataLsit?.forEach {
            list.add(TaskModel.map(it))
        }
        val data = MutableLiveData<List<TaskModel>>()
        data.value = list
        return data
    }*/
}