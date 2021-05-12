package com.example.listofspecialties.domain.usecase

import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.repository.WorkerRepository
import com.example.listofspecialties.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetWorkersUseCase @Inject constructor(
    private val repository: WorkerRepository
) : SingleUseCase<MutableList<WorkerDatabase>>() {

    private var specialityId: Int? = null

    fun saveSpecialityId(specialityId: Int?) {
        this.specialityId = specialityId
    }

    override fun buildUseCaseSingle(): Single<MutableList<WorkerDatabase>> {
        return repository.getWorkersBySpecialityId(specialityId)
    }
}