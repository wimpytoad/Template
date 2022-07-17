package com.toadfrogson.forgetmenot.data

import androidx.room.Room
import com.toadfrogson.forgetmenot.data.dao.TaskDao
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepo
import com.toadfrogson.forgetmenot.data.repo.DatabaseRepoImpl
import org.koin.dsl.module

class DataModule {

    companion object {
        val dataModule = module {
            single<DatabaseRepo> { DatabaseRepoImpl(get()) }
            single<TaskDB> {
                Room.databaseBuilder(
                    get(),
                    TaskDB::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
            single<TaskDao> {
                val database = get<TaskDB>()
                database.taskDao()
            }
        }
    }

}