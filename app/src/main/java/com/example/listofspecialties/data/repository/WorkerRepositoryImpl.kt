package com.example.listofspecialties.data.repository

import com.example.listofspecialties.data.source.local.AppDatabase
import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.repository.WorkerRepository
import io.reactivex.Single

class WorkerRepositoryImpl(
    private val database: AppDatabase
) : WorkerRepository {

    override fun getWorkersBySpecialityId(specialityId: Int?): Single<MutableList<WorkerDatabase>> {
        return database.workerDao.loadWorkerBySpecialtyId(specialityId)
    }

    override fun deleteWorker(worker: WorkerDatabase) {
        database.workerDao.delete(worker)
    }
}

