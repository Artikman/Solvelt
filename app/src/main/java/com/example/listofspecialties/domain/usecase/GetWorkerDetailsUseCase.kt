package com.example.listofspecialties.domain.usecase

import com.example.listofspecialties.domain.model.WorkerFavorite
import com.example.listofspecialties.domain.repository.WorkerDetailsRepository
import com.example.listofspecialties.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetWorkerDetailsUseCase @Inject constructor(
    private val repository: WorkerDetailsRepository
) : SingleUseCase<Boolean>() {

    private lateinit var worker: WorkerFavorite

    fun saveWorkerFavorite(worker: WorkerFavorite) {
        this.worker = worker
    }

    override fun buildUseCaseSingle(): Single<Boolean> {
        return repository.isFavorite(worker)
    }
}