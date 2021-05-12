package com.example.listofspecialties.data.repository

import com.example.listofspecialties.data.source.local.AppDatabase
import com.example.listofspecialties.data.source.remote.RetrofitService
import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.model.WorkerResponse
import com.example.listofspecialties.domain.repository.WorkerSpecialityRepository
import io.reactivex.Single

class WorkerSpecialityRepositoryImpl(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : WorkerSpecialityRepository {

    override fun getWorkers(): Single<WorkerResponse> {
        return retrofitService.getWorkers()
    }

    override fun addWorker(worker: WorkerDatabase) {
        database.workerDao.insert(worker)
    }

    override fun deleteAll() {
        database.workerDao.deleteAll()
    }
}