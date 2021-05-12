package com.example.listofspecialties.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listofspecialties.data.source.local.dao.WorkerDao
import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.model.WorkerFavorite

@Database(
    entities = [WorkerDatabase::class, WorkerFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val workerDao: WorkerDao

    companion object {
        const val DB_NAME = "WorkersDatabase.db"
    }
}