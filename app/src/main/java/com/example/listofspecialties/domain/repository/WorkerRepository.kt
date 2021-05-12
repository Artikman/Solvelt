package com.example.listofspecialties.domain.repository

import com.example.listofspecialties.domain.model.WorkerDatabase
import io.reactivex.Single

interface WorkerRepository {

    fun getWorkersBySpecialityId(specialityId: Int?): Single<MutableList<WorkerDatabase>>

    fun deleteWorker(worker: WorkerDatabase)
}