package com.toadfrogson.forgetmenot.data.repo

import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity

interface DatabaseRepo {
    fun getAllTasks(): List<SingleTaskEntity>
    fun getTasksByCategory(category: String) : List<SingleTaskEntity>
    fun saveTask(taskEntity: SingleTaskEntity)
}
class DatabaseRepoImpl(private val dao: TaskDao) : DatabaseRepo {

    override fun getAllTasks(): List<SingleTaskEntity> {
        val list = dao.getAll()
        list.size
        return listOf<SingleTaskEntity>(
            SingleTaskEntity(0, "Task One", "THis is it", false, "home"),
            SingleTaskEntity(1, "Task Two", "THis is it", false, "home"),
            SingleTaskEntity(2, "Task Three", "THis is it", false, "home"))
    }

    override fun getTasksByCategory(category: String): List<SingleTaskEntity> {
        return listOf<SingleTaskEntity>(
            SingleTaskEntity(0, "Task One", "THis is it", false, "home"),
            SingleTaskEntity(1, "Task Two", "THis is it", false, "home"),
            SingleTaskEntity(2, "Task Three", "THis is it", false, "home"))
    }

    override fun saveTask(taskEntity: SingleTaskEntity) {
        TODO("Not yet implemented")
    }
}