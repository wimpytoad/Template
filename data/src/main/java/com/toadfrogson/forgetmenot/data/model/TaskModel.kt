package com.toadfrogson.forgetmenot.data.model

import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity


data class TaskModel(
    val id: Int,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false,
    val category: String = "default"
) {
    companion object {
        fun map(entity: SingleTaskEntity?): TaskModel {
            return TaskModel(
                id = entity?.uid ?: 0,
                title = entity?.taskTitle ?: "",
                description = entity?.taskDescription ?: "",
                completed = entity?.taskCompletion ?: false,
                category = entity?.taskCategory ?: "default"
            )
        }

        fun convertToEntity(model: TaskModel): SingleTaskEntity {
            return SingleTaskEntity(
                taskTitle = model.title,
                taskDescription = model.description.orEmpty(),
                taskCompletion = model.completed,
                taskCategory = model.category
            )
        }
    }
}
