package com.example.listofspecialties.data.repository

import com.example.listofspecialties.data.source.local.AppDatabase
import com.example.listofspecialties.domain.model.WorkerFavorite
import com.example.listofspecialties.domain.repository.WorkerDetailsRepository
import io.reactivex.Single

class WorkerDetailsRepositoryImpl(
    private val database: AppDatabase
) : WorkerDetailsRepository {

    override fun deleteWorker(worker: WorkerFavorite) {
        database.workerDao.deleteWorkerFavorite(
            worker.lastName,
            worker.firstName,
            worker.birthday
        )
    }

    override fun addWorker(worker: WorkerFavorite) {
        database.workerDao.insertWorkerFavorite(worker)
    }

    override fun isFavorite(worker: WorkerFavorite): Single<Boolean> {
        val loadOneByPhotoId = database.workerDao.isFavorite(
            worker.lastName,
            worker.firstName,
            worker.birthday
        )
        return Single.just(loadOneByPhotoId != null)
    }
}
