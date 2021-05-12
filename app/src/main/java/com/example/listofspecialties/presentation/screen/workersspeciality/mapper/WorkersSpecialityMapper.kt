package com.example.listofspecialties.presentation.screeen.workersspeciality.mapper

import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.domain.model.WorkerResponse
import com.example.listofspecialties.presentation.model.Speciality
import javax.inject.Inject

class WorkersSpecialityMapper @Inject constructor() {

     fun convertWorkerResponseToSpeciality(response: WorkerResponse): List<Speciality> {
        val list: MutableList<Speciality> = mutableListOf()

        response.response?.forEach { worker ->
            worker.speciality?.forEach {
                list.add(
                    Speciality(
                        it.specialityId,
                        it.specialityName
                    )
                )
            }
        }
        return list.distinct()
    }

    fun convertWorkerResponseToWorker(response: WorkerResponse): List<WorkerDatabase> {
        val list: MutableList<WorkerDatabase> = mutableListOf()

        response.response?.forEach { worker ->
            worker.speciality?.forEach { speciality ->
                list.add(
                    WorkerDatabase(
                        0,
                        worker.firstName,
                        worker.lastName,
                        worker.birthday,
                        worker.avatarUrl,
                        speciality.specialityId,
                        speciality.specialityName
                    )
                )
            }
        }
        return list
    }
}