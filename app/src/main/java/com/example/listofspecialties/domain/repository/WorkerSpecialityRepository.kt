package com.example.listofspecialties.domain.repository

import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.model.WorkerResponse
import io.reactivex.Single

interface WorkerSpecialityRepository {

    fun getWorkers(): Single<WorkerResponse>

    fun addWorker(worker: WorkerDatabase)

    fun deleteAll()
}