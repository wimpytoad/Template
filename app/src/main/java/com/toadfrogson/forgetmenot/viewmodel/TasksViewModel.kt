package com.toadfrogson.forgetmenot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.data.model.TaskModel
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepo

class TasksViewModel(private val repo: DatabaseRepo): ViewModel() {

    val allTasks: LiveData<List<TaskModel>> = repo.tasksList
    //val categoryResults: MutableLiveData<List<SingleTaskEntity?>>


}