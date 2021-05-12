package com.example.listofspecialties.domain.repository

import com.example.listofspecialties.domain.model.WorkerFavorite
import io.reactivex.Single

interface WorkerDetailsRepository {

    fun deleteWorker(worker: WorkerFavorite)

    fun addWorker(worker: WorkerFavorite)

    fun isFavorite(worker: WorkerFavorite): Single<Boolean>
}