package com.example.listofspecialties.domain.usecase

import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.model.WorkerResponse
import com.example.listofspecialties.domain.repository.WorkerSpecialityRepository
import com.example.listofspecialties.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetWorkersSpecialityUseCase @Inject constructor(
    private val repository: WorkerSpecialityRepository
) : SingleUseCase<WorkerResponse>() {
    override fun buildUseCaseSingle(): Single<WorkerResponse> {
        return repository.getWorkers()
    }

    fun addWorkers(workers: List<WorkerDatabase>) {
        workers.forEach {
            repository.addWorker(it)
        }
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}