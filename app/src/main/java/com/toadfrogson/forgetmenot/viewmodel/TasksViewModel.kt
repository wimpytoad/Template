package com.toadfrogson.forgetmenot.viewmodel

import androidx.lifecycle.ViewModel
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepo

class TasksViewModel(private val repo: DatabaseRepo): ViewModel() {

    fun getTasks() : List<SingleTaskEntity> {
        return repo.getAllTasks()
    }

}